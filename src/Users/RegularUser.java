package Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import interfaces.FileHandler;
import interfaces.Readable;

import static Users.AdminUser.USER_FILE;

public class RegularUser extends User implements Readable {
    public RegularUser(String userId,String username,String email,String password) {
        super(userId,username,email,password,"Regular");
    }

    @Override
    public void viewUser() {
        List<String[]> users = FileHandler.readFile(USER_FILE);
        users.forEach(record -> System.out.println(String.join(", ", record)));
    }

}
