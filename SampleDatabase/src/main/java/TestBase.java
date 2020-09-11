import java.sql.*;

public class TestBase {

    public static String url = "jdbc:mysql://localhost:3360/sample_db";
    public static String user      = "root";
    public static String password  = "Active@2020";


    // Method to insert, select and delete record in student table
    public void apiRequest(String studentName, String department, char Method, String sql){

        Connection conn = null;

        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connected to database");

            switch (Method){
                case 'P':
                    //Insert first record
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, studentName);
                    stmt.setString(2, department);
                    int rows = stmt.executeUpdate();
                    if(rows >0){
                        System.out.println("row has been inserted");
                    }
                    break;
                case 'S':
                    Statement stmt1 = conn.createStatement();
                    ResultSet result = stmt1.executeQuery(sql);
                    int count = 0;
                    while(result.next()){
                        String name = result.getString("studentName");
                        count ++;
                        System.out.println(count + " " + name);
                    }
                    break;
                case 'D':
                    PreparedStatement stmt2 = conn.prepareStatement(sql);
                    stmt2.executeUpdate();
                    break;
                default:
                    System.out.println("invalid method call");
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

    //Method to create table
    public void createTable(){


    }

}
