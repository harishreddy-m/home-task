package harish.task.app;

import harish.task.app.handlers.AccountController;
import harish.task.app.handlers.TransactionController;
import harish.task.app.repository.AccountRepository;
import harish.task.app.repository.TransactionRepository;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.crud;

public class BankApp {

    public void init(){
        Javalin app = Javalin.create()
                .start();
        app.get("/",ctx -> ctx.html("Welcome to Bank API"));

        app.routes(() -> {
            crud("account/:account-id", new AccountController(new AccountRepository()));
            crud("transaction/:account-id", new TransactionController(new TransactionRepository()));
        });
    }

    public static void main(String[] args) {
     new BankApp().init();
    }
}
