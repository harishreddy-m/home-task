package harish.task.app;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;

public class AppTest extends FunctionalTest {


    @Test
    public void givenUrl_thenSuccess() {
        get("/").then().statusCode(200)
                .body(containsString("Bank API"));
    }
}
