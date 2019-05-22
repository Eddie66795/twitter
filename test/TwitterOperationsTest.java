import main.twitter.application.User;
import main.twitter.domain.TwitterOperations;
import main.twitter.error.InvalidTweetException;
import main.twitter.error.TwitterOperationException;
import main.twitter.error.UserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterOperationsTest {

    private TwitterOperations twitterOperations;

    @Before
    public void setUp() {
        twitterOperations = new TwitterOperations();
    }

    @Test
    public void assertSingleUserCreatedTest() throws UserException {
        String userName = "Ward";
        twitterOperations.addUser(userName);
        User newUserWard = twitterOperations.getUser(userName);

        Assert.assertNotNull(newUserWard);
        Assert.assertEquals(0, newUserWard.getFollowersList().size());
        Assert.assertEquals(1, twitterOperations.getlistUsers().size());
    }

    @Test
    public void assertSingleUserAndFollowerCreatedTest() throws UserException {
        String currentUserName = "Ward";
        String followerUserName = "Alan";

        twitterOperations.addFollower(currentUserName, followerUserName);

        // Assert both users was created
        Assert.assertEquals(2, twitterOperations.getlistUsers().size());

        //Assert that follower is a valid user
        User userWard = twitterOperations.getUser(currentUserName);
        Assert.assertEquals(1, userWard.getFollowersList().size());
        Assert.assertEquals(followerUserName, userWard.getFollowersList().get(0).getName());
    }

    @Test
    public void assertUserTweetsCreatedTest() throws UserException, InvalidTweetException, TwitterOperationException {
        String currentUserName = "Ward";
        String tweetText = "TEST";

        twitterOperations.addUser(currentUserName);
        twitterOperations.addTweet(currentUserName, tweetText);

        User currentUser = twitterOperations.getUser(currentUserName);
        Assert.assertEquals(1, currentUser.getUserTweets().size());
    }

    @Test(expected = main.twitter.error.UserException.class)
    public void assertUserTweetsRaisesException() throws UserException, InvalidTweetException, TwitterOperationException {
        String currentUserName = "Ward";
        String tweetText = "TEST";
        try {
            twitterOperations.addTweet(currentUserName, tweetText);
        } catch (UserException e) {
            String exception = "USER DOES NOT EXIST: ".concat(currentUserName);
            Assert.assertEquals(exception, e.getMessage());
            throw new UserException(exception);
        }
    }
}
