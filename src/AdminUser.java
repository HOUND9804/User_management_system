import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class AdminUser extends User implements Readable,Writeable,AdminPrivilege{
    public AdminUser(String userId,String usename,String email, String password,String type) {
        super(userId,usename,email,password,"Admin");
    }

    @Override
    public void updateUserPrivilege(String userId, String privilege) {
        try {
            boolean userFound=false;
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                String[] parts = line.split(",");
                if(parts[0].equals(userId)){
                    userFound = true;
                    if(privilege.equals("Admin")){
                        addAdmin(parts[0],parts[1],parts[2],parts[3]);
                    }
                    else{
                        parts[4]=privilege;
                        addUser(parts[0],parts[1],parts[2],parts[3],parts[4]);
                    }
                }
                line = br.readLine();
            }
            br.close();
            if(userFound){
                System.out.println("user found");
            }
            else{
                System.out.println("user not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserInfo(String userId, String oldInfo, String newInfo) {

    }

    @Override
    public void addAdmin(String userId, String username, String email, String password) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("admin.txt",true))) {
            bw.append(userId).append(",").append(username).append(",").append(email).append(",").append(password);
            bw.newLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewAdmins() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("admin.txt"));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewUser() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String userId, String username, String email, String password, String type) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt",true))) {
            bw.append(userId).append(",").append(username).append(",").append(email).append(",").append(password).append(",").append(type);
            bw.newLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
