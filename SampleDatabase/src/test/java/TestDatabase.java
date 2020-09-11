import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class TestDatabase extends TestBase {

    static Connection conn = null;

    @BeforeClass
    public static void setUp(){
       try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connected to database");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertData(){
        //Insert first record
        try {
            String sql = "INSERT INTO student (studentName, department) VALUES (? , ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Pretty");
            stmt.setString(2, "ECE");
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("row has been inserted");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getData(){
        //Select Record
        try {
            String sql = "SELECT * From student WHERE department = 'ECE'";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int count = 0;
            while(result.next()){
                String studentName = result.getString("studentName");
                count ++;
                System.out.println(count + " " + studentName);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteData(){
        //Select Record
        try {
            String sql = "delete From student where id = 6";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public static void tearDown(){
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
