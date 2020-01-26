package harish.task.app;

import harish.task.app.handlers.AccountController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class BankApp {
    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .start();
        app.get("/",ctx -> ctx.html("Welcome to Bank API"));
        app.routes(() -> {
            path("account", () -> {
                post(AccountController::createAccount);
                path(":id", () -> {
                    get(AccountController::getAccount);
                    patch(AccountController::updateAccount);
                    delete(AccountController::deleteAccount);
                });
            });
        });
    }
}
