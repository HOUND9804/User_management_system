public interface AdminPrivilege {
    public void updateUserPrivilege(String userId, String privilege);
    public void updateUserInfo(String userId, String oldInfo,String newInfo);
    public void addAdmin(String userId,String username,String email,String password);
    public void viewAdmins();
}
