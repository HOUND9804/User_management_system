package interfaces;

public interface Writeable {
    void viewUser();

    public void addUser(String userId, String userName, String email, String password, String type);
}
