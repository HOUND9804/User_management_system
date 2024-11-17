package Users;

import interfaces.AdminPrivilege;
import interfaces.Readable;
import interfaces.Writeable;
import interfaces.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User implements Readable, Writeable, AdminPrivilege {
    static final String USER_FILE = "user.csv";
    private static final String ADMIN_FILE = "admin.csv";

    public AdminUser(String userId, String username, String email, String password) {
        super(userId, username, email, password, "Admin");
    }

    @Override
    public void viewUser() {
        List<String[]> users = FileHandler.readFile(USER_FILE);
        users.forEach(record -> System.out.println(String.join(", ", record)));
    }

    @Override
    public void addUser(String userId, String username, String email, String password, String type) {
        String record = String.join(",", userId, username, email, password, type);
        FileHandler.appendToFile(USER_FILE, record);
    }

    @Override
    public void updateUserPrivilege(String userId, String privilege) {

        List<String[]> users = FileHandler.readFile(USER_FILE);
        List<String[]> admins = FileHandler.readFile(ADMIN_FILE);
        boolean updated = false;

        for (int i = 0; i < users.size(); i++) {
            String[] record = users.get(i);
            if (record[0].equals(userId)) {
                updated = true;
                if ("Admin".equalsIgnoreCase(privilege)) {
                    this.addAdmin(record[0], record[1], record[2], record[3]);
                    users.remove(i);
                } else {
                    record[4] = privilege;
                    users.set(i, record);
                }
                break;
            }
        }

        if (updated) {
            FileHandler.writeFile(USER_FILE, users);
            System.out.println("User privilege updated successfully.");
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }



    @Override
    public void updateUserInfo(String userId, String oldInfo, String newInfo) {
        List<String[]> users = FileHandler.readFile(USER_FILE);
        boolean updated = false;

        for (String[] record : users) {
            if (record[0].equals(userId)) {
                for (int i = 0; i < record.length-2; i++) {
                    if (record[i].equals(oldInfo)) {
                        record[i] = newInfo; // Update info
                        updated = true;
                        break;
                    }
                }
            }
        }

        if (updated) {
            FileHandler.writeFile(USER_FILE, users);
        } else {
            System.out.println("User or info not found.");
        }
    }

    @Override
    public void addAdmin(String userId, String username, String email, String password) {
        String record = String.join(",", userId, username, email, password);
        FileHandler.appendToFile(ADMIN_FILE, record);
    }

    @Override
    public void viewAdmins() {
        List<String[]> admins = FileHandler.readFile(ADMIN_FILE);
        admins.forEach(record -> System.out.println(String.join(", ", record)));
    }
}
