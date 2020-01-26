package harish.task.app;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;

public class AccountFunctionalTest {


    @BeforeClass
    public static void setup() {
        new BankApp().init();
        RestAssured.port = 7000;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void givenNoAccounts_thenEmptyList() {
        get("/account").then().statusCode(200);
    }
}
