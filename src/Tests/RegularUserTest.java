package Tests;

import Users.PowerUser;
import Users.RegularUser;
import Users.User;
import org.junit.jupiter.api.Test;

public class RegularUserTest {
    RegularUser user = new RegularUser("1998", "hound98", "mustainahmedtaj@gmail.com", "1998");
    @Test
    public void testRegularUser() {
        user.viewUser();
    }
}
