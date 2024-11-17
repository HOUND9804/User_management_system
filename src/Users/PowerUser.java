package Users;

import interfaces.FileHandler;
import interfaces.Writeable;
import interfaces.Readable;
import java.util.List;

import static Users.AdminUser.USER_FILE;

public class PowerUser extends User implements Readable, Writeable {

    public PowerUser(String userId,String username,String email,String password) {
        super(userId,username,email,password,"Power");
    }

    @Override
    public void viewUser() {
        List<String[]> users = FileHandler.readFile(USER_FILE);
        users.forEach(record -> System.out.println(String.join(", ", record)));
    }

    @Override
    public void addUser(String userId,String username,String email,String password,String type) {
        String record = String.join(",", userId, username, email, password, type);
        FileHandler.appendToFile(USER_FILE, record);
    }
}
