package harish.task.app;

import io.javalin.Javalin;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;

public class AppTest {


    @BeforeClass
    public static void setup() {
        new BankApp().init();
        RestAssured.port = 7000;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void givenUrl_thenSuccess() {
        get("/").then().statusCode(200)
                .body(containsString("Bank API"));
    }
}
