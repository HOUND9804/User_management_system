package Main;

import Users.*;
import java.util.Scanner;

public class UserInterface {
    private static final String USER_FILE_PATH = "user.csv";
    private static final String ADMIN_FILE_PATH = "admin.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAuthentication userAuth;

        System.out.println("Welcome to the User Management System");
        System.out.println("Please select your role:");
        System.out.println("1. User");
        System.out.println("2. Admin");

        int roleChoice = getIntegerInput(scanner, "Enter your choice (1 or 2): ");
        String filePath;

        if (roleChoice == 1) {
            System.out.println("You selected User. Please log in.");
            filePath = USER_FILE_PATH;
        } else if (roleChoice == 2) {
            System.out.println("You selected Admin. Please log in.");
            filePath = ADMIN_FILE_PATH;
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        userAuth = new UserAuthentication(filePath);

        // Login
        System.out.print("Enter user ID: ");
        String userid = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userAuth.authenticate(userid, password);
        if (user == null) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        System.out.println("Login successful. Welcome, " + user.username);

        while (true) {
            displayMenu(user);

            int choice = getIntegerInput(scanner, "Enter your choice: ");
            switch (choice) {
                case 1 -> viewUsers(user);
                case 2 -> addUser(scanner, user);
                case 3 -> updatePrivileges(scanner, user);
                case 4 -> viewAdmins(user);
                case 5 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu(User user) {
        System.out.println("\nAvailable options:");
        System.out.println("1. View Users");
        if (user instanceof AdminUser || user instanceof PowerUser) {
            System.out.println("2. Add User");
        }
        if (user instanceof AdminUser) {
            System.out.println("3. Update Privileges");
            System.out.println("4. View Admins");
        }
        System.out.println("5. Exit");
    }

    private static void viewUsers(User user) {
        if (user instanceof RegularUser) {
            ((RegularUser) user).viewUser();
        }
        else if(user instanceof AdminUser) {
            ((AdminUser) user).viewUser();
        }
        else if (user instanceof PowerUser) {
            ((PowerUser) user).viewUser();
        }
        else {
            System.out.println("You do not have permission to view users.");
        }
    }

    private static void addUser(Scanner scanner, User user) {
        if (user instanceof AdminUser || user instanceof PowerUser) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Enter Role (Regular/Power/Admin): ");
            String role = scanner.nextLine();

            if (user instanceof AdminUser) {
                ((AdminUser) user).addUser(userId, newUsername, email, newPassword, role);
            } else {
                ((PowerUser) user).addUser(userId, newUsername, email, newPassword, role);
            }
        } else {
            System.out.println("You do not have permission to add users.");
        }
    }

    private static void updatePrivileges(Scanner scanner, User user) {
        if (user instanceof AdminUser) {
            System.out.print("Enter User ID to update privilege: ");
            String userId = scanner.nextLine();
            System.out.print("Enter new privilege (Regular/Power/Admin): ");
            String privilege = scanner.nextLine();

            ((AdminUser) user).updateUserPrivilege(userId, privilege);
        } else {
            System.out.println("You do not have permission to update privileges.");
        }
    }

    private static void viewAdmins(User user) {
        if (user instanceof AdminUser) {
            ((AdminUser) user).viewAdmins();
        } else {
            System.out.println("You do not have permission to view admins.");
        }
    }

    private static int getIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
