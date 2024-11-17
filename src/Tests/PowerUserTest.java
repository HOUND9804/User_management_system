package Tests;

import Users.PowerUser;
import org.junit.jupiter.api.Test;

public class PowerUserTest {
    PowerUser user = new PowerUser("1998", "hound98", "mustainahmedtaj@gmail.com", "1998");
    @Test
    public void testAdd() {
        user.addUser("1998", "hound98", "mustainahmedtaj@gmail.com", "1998", "Regular");
    }
    @Test
    public void testview(){
        user.viewUser();
    }

}
