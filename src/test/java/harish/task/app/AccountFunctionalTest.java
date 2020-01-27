package harish.task.app;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;

public class AccountFunctionalTest extends FunctionalTest{

    @Test
    public void givenNoAccounts_thenEmptyList() {
        get("/account").then().statusCode(200)
                .body("$", hasKey("accounts"))
                .assertThat().body("accounts.size()",is(0));
    }

    @Test
    public void given_thenAccountCreated() {
        given().contentType("application/json")
                .body("{\"balance\":12.30}")
                .when().post("/account")
                .then().statusCode(201).header("Location",containsString("account"));
    }

    @Test
    public void givenAccount_thenAccountDeleted() {
        Response response = given().contentType("application/json")
                .body("{\"balance\":12.30}")
                .when().post("/account");
        String accountCreated = response.header("Location").replaceAll("account/","");
                delete("/account/"+accountCreated)
                .then().statusCode(204);
    }
}
