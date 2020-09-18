import java.sql.*;

public class TestBase {

    public static String url = "jdbc:mysql://localhost:3360/sample_db";
    public static String user      = "root";
    public static String password  = "Active@2020";


    public boolean getRecord(String StudentName){
        Connection conn = null;
        String sql = "SELECT * From student WHERE studentName = '" + StudentName + "' ";


        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //Select Record
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int count = 0;
            while(result.next()){
                String studentName = result.getString("studentName");
                count ++;
                System.out.println(count + " " + studentName);
            }
            if(count >=1){
                return true;
            }else{
                System.out.println(" student record is not found with name " + StudentName );
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    public boolean deleteRecord(String StudentName){
        Connection conn = null;
        String sql = "delete From student where studentName = '" + StudentName + "' ";

        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //Delete Record
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            int rows = stmt2.executeUpdate();
            if(rows >0){
                System.out.println("row has been deleted");
                return true;
            }else {
                System.out.println("record does not exist");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        } finally {
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    public boolean addStudent(String studentName, String department){
        Connection conn = null;
        String sql = "INSERT INTO student (studentName, department) VALUES (? , ?)";

        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connected to database");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentName);
            stmt.setString(2, department);
            int rows = stmt.executeUpdate();
            if(rows >0){
                System.out.println("row has been inserted");
                return true;
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
        return false;
    }

    public boolean getAllData(String tableName){
        Connection conn = null;
        String sql = "SELECT * From " + tableName + " ";


        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //Select Record
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int count = 0;
            while(result.next()){
                String studentName = result.getString("studentName");
                count ++;
                System.out.println(count + " " + studentName);
            }
            if(count >=1){
                return true;
            }else{
                System.out.println("record with name jammy is already present");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }



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

}
