import main.twitter.application.User;

import org.junit.Assert;
import org.junit.Test;

public class UserTests {

    @Test
    public void assertUserNameIsSet() {
        String userName = "Alan";
        Assert.assertEquals(userName, new User(userName).getName());
    }

    @Test
    public void assertUserNameNull() {
        String emptyName = null;
        Assert.assertFalse(new User(emptyName).isValid());
    }

    @Test
    public void assertUserNameEmpty() {
        String emptyName = "";
        Assert.assertFalse(new User(emptyName).isValid());
    }

    @Test
    public void assertFollowerAddedSuccessfully() {
        // Alan follows Ward
        User currentUser = new User("Ward");
        User followerUser = new User("Alan");

        currentUser.addFollower(followerUser);
        Assert.assertTrue(currentUser.getFollowersList().contains(followerUser));
    }
}
