import main.twitter.application.Tweets;
import org.junit.Assert;
import org.junit.Test;

public class TweetsTest {

    @Test
    public void assertTweetPropertiesSet() {
        String owner = "ward";
        String textValue = "Text goes here";
        Tweets tweets = new Tweets(owner, textValue);

        Assert.assertTrue(tweets.isValid());
        Assert.assertEquals(tweets.getOwner(), owner);
        Assert.assertEquals(tweets.getTextValue(), textValue);
    }

    @Test
    public void assertTweetValidation() {
        Tweets faultyTweet1 = new Tweets("", "");
        Tweets faultyTweet2 = new Tweets(null, null);

        Assert.assertFalse(faultyTweet1.isValid());
        Assert.assertFalse(faultyTweet2.isValid());
    }

    public Tweets createAndValidateTweet(Tweets faultyTweet) {
        faultyTweet.isValid();

        return faultyTweet;
    }

    @Test
    public void assertTweetValidationErrorMessage() {
        Tweets faultyTweet1 = createAndValidateTweet(new Tweets("", ""));
        Assert.assertEquals("VIOLATION: OWNER FIELD EMPTY OR NULL", faultyTweet1.getViolationMessage());

        Tweets faultyTweet2 = createAndValidateTweet(new Tweets(null, null));
        Assert.assertEquals("VIOLATION: OWNER FIELD EMPTY OR NULL", faultyTweet2.getViolationMessage());

        Tweets faultyTweet3 = createAndValidateTweet(new Tweets(null, null));
        Assert.assertEquals("VIOLATION: OWNER FIELD EMPTY OR NULL", faultyTweet3.getViolationMessage());

        Tweets faultyTweet4 = new Tweets("Ward", "Tweet");
        faultyTweet4.setDateTime(null);
        faultyTweet4.isValid();
        Assert.assertEquals("VIOLATION: DATE FIELD NOT SET", faultyTweet4.getViolationMessage());

        String faultyTweet5Text = new String(new char[141]).replace('\0', 'a');
        Tweets faultyTweet5 = new Tweets("Ward", faultyTweet5Text);
        faultyTweet5.isValid();
        Assert.assertEquals("VIOLATION: TEXTVALUE > 140 CHARS", faultyTweet4.getViolationMessage());
    }
}
