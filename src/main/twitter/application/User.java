package main.twitter.application;

import java.util.LinkedList;
import java.util.TreeMap;

public class User {

    private String userName;
    private LinkedList<User> followersList;
    private TreeMap<Integer, Tweets> usersTweetsList;

    public User(final String userName) {
        this.userName = userName;
        followersList = new LinkedList<User>();
        usersTweetsList = new TreeMap<Integer, Tweets>();
    }

    public boolean isValid() {
        if (userName == null || userName.isEmpty()) {
            return false;
        }
        return true;
    }

    public void addFollower(final User userName) {
        followersList.add(userName);
    }

    public String getName() {
        return userName;
    }

    public LinkedList<User> getFollowersList() {
        return followersList;
    }

    public void addTweet(int tweetIndex, Tweets tweet) {
        usersTweetsList.put(tweetIndex, tweet);
    }

    public TreeMap<Integer, Tweets> getUserTweets() {
        return usersTweetsList;
    }
}
