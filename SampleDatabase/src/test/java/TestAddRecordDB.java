import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddRecordDB extends TestBase{

    @BeforeClass
    public static void setUp(){
        System.out.println("connecting to database ........");
    }

    @Test
    public void test001AllData(){
        Assert.assertTrue(getAllData("student"));
    }

    @Test
    public void test002InsertData(){
        boolean flag = getRecord("Jammy");
        if(flag){
            Assert.assertTrue(deleteRecord("Jammy"));
        }
        Assert.assertTrue(addStudent("Jammy", "ECE"));
        Assert.assertTrue(getRecord("Jammy"));
    }

    @Test
    public void test003GetData(){
        Assert.assertTrue(getRecord("pretty"));
    }

    @Test
    public void test004DeleteData(){
        Assert.assertTrue(deleteRecord("pretty"));
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("exit");
    }

}
