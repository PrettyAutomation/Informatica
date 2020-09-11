import java.sql.*;

public class Sample {

    public static void main(String [] args) {
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3360/sample_db";
            String user      = "root";
            String password  = "Active@2020";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connected to database");

            //Insert first record
            String sql  ="INSERT INTO student (studentName, department) VALUES (? , ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "jhon");
            stmt.setString(2, "CSE");
            int rows = stmt.executeUpdate();
            if(rows >0){
                 System.out.println("row has been inserted");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
