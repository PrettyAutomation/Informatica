
import org.junit.Test;

public class APITestSingleTable extends TestBase{

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
        String sql = "delete From student where id = 6";
        apiRequest(null,null,'D',sql);
    }

}