package main.twitter.domain;

import main.twitter.application.Tweets;
import main.twitter.application.User;
import main.twitter.error.InvalidTweetException;
import main.twitter.error.TwitterOperationException;
import main.twitter.error.UserException;

import java.util.*;

public class TwitterOperations {

    private LinkedList<Tweets> listTweets;
    private TreeMap<String, User> listUsers;

    public TwitterOperations() {
        listUsers = new TreeMap<String, User>();
        listTweets = new LinkedList<Tweets>();
    }

    /**
     * Append a new user
     * @param currentUserUserName - Username of the currentUser (pending validation)
     * @throws UserException
     */
    public void addUser(String currentUserUserName) throws UserException {
        User user = new User(currentUserUserName);
        if (user.isValid()) {
            listUsers.put(currentUserUserName, user);
        } else {
            throw new UserException("USERNAME BLANK");
        }
    }

    /**
     * Append a follower to the users list of followers
     * @param currentUserUserName - Username of the currentUser
     * @param followerUserUserName - UserName of the followerUser
     * @throws UserException
     */
    public void addFollower(String currentUserUserName, String followerUserUserName) throws UserException {
        //Check if user exists
        if (getUser(currentUserUserName) == null) {
            addUser(currentUserUserName);
        }

        //Check if follower exists
        if (getUser(followerUserUserName) == null) {
            addUser(followerUserUserName);
        }

        User currentUser = getUser(currentUserUserName);
        User currentUsersFollower = getUser(followerUserUserName);
        currentUser.addFollower(currentUsersFollower);
    }

    /**
     * Append incoming tweet to:
     *      1. Global: listTweets
     *      2. Users: usersTweetsList
     * @param userName - Owner of the tweet
     * @param tweetText - TextBody of the tweet
     * @throws UserException
     * @throws InvalidTweetException
     */
    public void addTweet(String userName, String tweetText) throws UserException, InvalidTweetException, TwitterOperationException {

        if(getlistUsers().size() == 0) {
            throw new TwitterOperationException("USER LIST EMPTY");
        } else {
            if (getUser(userName).isValid()) {
                Tweets newTweet = new Tweets(userName, tweetText);
                if (newTweet.isValid()) {
                    listTweets.add(newTweet);
                    getUser(userName).addTweet(getlistTweets().size(), newTweet);
                } else {
                    throw new InvalidTweetException("TWEET VALIDATION FAILED: " + newTweet.getViolationMessage());
                }
            } else {
                throw new UserException("USER DOES NOT EXIST: " + userName);
            }
        }
    }

    public User getUser(String userName) {
        return listUsers.get(userName);
    }

    public TreeMap<String, User> getlistUsers() {
        return listUsers;
    }

    public LinkedList<Tweets> getlistTweets() {
        return listTweets;
    }
}