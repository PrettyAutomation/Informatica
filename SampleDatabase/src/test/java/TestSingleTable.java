
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSingleTable extends TestBase{

    @BeforeClass
    public static void setUp(){
        System.out.println("connected to database");
    }

    @Test
    public void test001InsertData(){
        String sql = "INSERT INTO student (studentName, department) VALUES (? , ?)";
        apiRequest("Jammy","ECE",'P',sql);
    }

    @Test
    public void test002GetData(){
        String sql = "SELECT * From student WHERE department = 'ECE'";
        apiRequest(null,null,'S',sql);
    }

    @Test
    public void test003DeleteData(){
        String sql = "delete From student where studentName = 'Jammy'";
        apiRequest(null,null,'D',sql);
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("exit");
    }

}
