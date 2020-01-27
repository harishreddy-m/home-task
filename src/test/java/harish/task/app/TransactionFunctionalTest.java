package harish.task.app;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class TransactionFunctionalTest extends FunctionalTest{

    @Test
    public void givenTwoAccounts_thenTransactionCreated() {
        Response response = given().contentType("application/json")
                .body("{\"balance\":12.30}")
                .when().post("/account");
        String accountFrom = response.header("Location").replaceAll("account/","");

        response = given().contentType("application/json")
                .body("{\"balance\":2.30}")
                .when().post("/account");
        String accountTo = response.header("Location").replaceAll("account/","");


        given().contentType("application/json")
                .body(String.format("{\"amount\":2.30,\"fromAccount\":%s,\"toAccount\":%s}",accountFrom,accountTo))
                .when().post("/transaction")
                .then().statusCode(204);

        get("/account/"+accountTo).then().statusCode(200)
                .body("$", hasKey("transactions"))
                .assertThat()
                .body("transactions.size()",is(1))
                .body("balance",is(4.6f))
                .body("transactions[0].amount",is(2.3f));

        get("/account/"+accountFrom).then().statusCode(200)
                .body("$", hasKey("transactions"))
                .assertThat().body("transactions.size()",is(1))
                .body("balance",is(10.0f))
                        .body("transactions[0].amount",is(-2.3f));

        delete("/account/"+accountFrom)
                .then().statusCode(204);

        delete("/account/"+accountTo)
                .then().statusCode(204);

    }
}
