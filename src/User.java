public class User {
    protected String userid;
    protected String username;
    protected String password;
    protected String email;
    protected String usertype;

    public User(String userid, String username, String password, String email, String usertype) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.usertype = usertype;
    }
}
