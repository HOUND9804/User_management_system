package Main;

import Users.*;
import java.util.List;
import interfaces.FileHandler;

public class UserAuthentication {
    private String url;

    public UserAuthentication(String url) {
        this.url = url;
    }

    public User authenticate(String userid, String password) {
        List<String[]> users = FileHandler.readFile(url);

        for (String[] parts : users) {

            if (parts[0].equalsIgnoreCase(userid) && parts[3].equals(password)) {

                if (parts.length == 4) {
                    return new AdminUser(parts[0], parts[1], parts[2], parts[3]);
                } else if (parts.length == 5) {
                    String role = parts[4].toLowerCase();
                    return createUser(role, parts);
                }
            }
        }

        System.out.println("Invalid username or password.");
        return null;
    }



    private User createUser(String role, String[] parts) {
        switch (role) {
            case "admin":
                return new AdminUser(parts[0], parts[1], parts[2], parts[3]);
            case "power":
                return new PowerUser(parts[0], parts[1], parts[2], parts[3]);
            case "regular":
                return new RegularUser(parts[0], parts[1], parts[2], parts[3]);
            default:
                throw new IllegalArgumentException("Unrecognized role: " + role);
        }
    }
}
