import java.io.BufferedReader;
import java.io.FileReader;

public class RegularUser extends User implements Readable {
    public RegularUser(String userId,String username,String email,String password) {
        super(userId,username,email,password,"Regular");
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
}
