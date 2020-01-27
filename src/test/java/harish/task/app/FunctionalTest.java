package harish.task.app;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class FunctionalTest {
    static BankApp testServer;

    @BeforeClass
    public static void setup() {
        testServer = new BankApp();
        testServer.init();
        RestAssured.port = 7000;
        RestAssured.baseURI = "http://localhost";
    }

    @AfterClass
    public static void destroy() {
        testServer.destroy();
    }
}
