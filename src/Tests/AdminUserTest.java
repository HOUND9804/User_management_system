package Tests;

import Users.AdminUser;
import Users.User;
import org.junit.jupiter.api.Test;

public class AdminUserTest {
  AdminUser admin =new AdminUser("1234", "Admin", "xzy@gmail.com", "Admin");
  @Test
  public void testupdatePrivileges(){
      admin.updateUserPrivilege("1998","Admin");
  }
  @Test
    public void testviewUser(){
      admin.viewUser();
  }
  @Test
    public void testaddAdmin(){
      admin.addAdmin("1999","setu", "setu@gmail.com", "Admin");
  }
  @Test
    public void testViewAdmin(){
      admin.viewAdmins();
  }
}
