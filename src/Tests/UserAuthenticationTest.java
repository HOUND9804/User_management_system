package Tests;

import Main.UserAuthentication;
import Users.AdminUser;
import Users.RegularUser;
import Users.User;
import org.junit.jupiter.api.Test;

public class UserAuthenticationTest {
    UserAuthentication uau=new UserAuthentication("admin.csv");
    @Test
    public void authenticateTest() {
        User u = (RegularUser) uau.authenticate("1998", "1998");
        ((RegularUser) u).viewUser();
    }
    @Test
    public void authenticateTest2() {
        User u = (AdminUser) uau.authenticate("1999", "Admin");
        ((AdminUser) u).viewUser();
    }
}
