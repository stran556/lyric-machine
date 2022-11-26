import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    public static void main (String[] args){
        
        try{
            //Create connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lyricmachine","root", "777Echoes+");

            if (con != null){
                System.out.println("Successful connection");
            }
            else{
                System.out.println("Error.");
            }
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * from UserList");

            while (rs.next()){
                System.out.println(rs.getString("title"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
