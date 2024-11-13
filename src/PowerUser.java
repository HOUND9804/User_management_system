import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class PowerUser extends User implements Readable,Writeable{
    public PowerUser(String userId,String username,String email,String password,String type) {
        super(userId,username,email,password,"Power");
    }

    @Override
    public void read() {
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
    public void write(String userId,String userName,String email,String password,String type) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt",true))) {
            bw.append(userId).append(",").append(userName).append(",").append(email).append(",").append(password).append(",").append(type);
            bw.newLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
