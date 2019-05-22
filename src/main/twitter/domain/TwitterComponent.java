package main.twitter.domain;

import main.twitter.application.Tweets;
import main.twitter.application.User;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TwitterComponent extends TwitterOperations {

    /**
     * Generate the twitter field as requested
     */
    public void generateTwitterFeed() {
        Iterator iterator2 = getlistUsers().entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator2.next();
            displayUsersTwitterFeed(pair.getKey().toString());
            iterator2.remove();
        }
    }

    /**
     * Display the feed for the currentUser user
     *
     * @param currentUserUserName
     */
    private void displayUsersTwitterFeed(String currentUserUserName) {
        System.out.println(currentUserUserName);
        TreeMap<Integer, Tweets> usersTwitterFeed = getUsersTwitterFeed(currentUserUserName);
        Iterator iterator2 = usersTwitterFeed.entrySet().iterator();

        while (iterator2.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator2.next();

            Tweets tweet_tmp = (Tweets) mapEntry.getValue();
            System.out.println("\t" + tweet_tmp.getFormattedTextValue());
        }
    }

    /**
     * 1. Obtain the twitterFeed for the currentUser
     * 2. Obtain the twitterFeed for the currentUser's followers
     *
     * @param currentUserUserName
     * @return TreeMap of the currentUser and the the currentUser's followers feed.
     */
    private TreeMap<Integer, Tweets> getUsersTwitterFeed(String currentUserUserName) {
        TreeMap<Integer, Tweets> tmpTweetList = getlistUsers().get(currentUserUserName).getUserTweets();
        for (User follower : getlistUsers().get(currentUserUserName).getFollowersList()) {
            tmpTweetList.putAll(follower.getUserTweets());

        }
        return tmpTweetList;
    }

}
