package harish.task.app;

import harish.task.app.dao.HibernateConfig;
import harish.task.app.exception.FailedTransaction;
import harish.task.app.handlers.AccountController;
import harish.task.app.handlers.TransactionController;
import harish.task.app.dao.AccountManager;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.crud;
import static io.javalin.apibuilder.ApiBuilder.post;

public class BankApp {
    private Javalin app;
    public void init(){
        app = Javalin.create()
                .start();
        app.get("/",ctx -> ctx.html("Welcome to Bank API"));
        app.exception(FailedTransaction.class, (e, ctx) -> {
            ctx.status(400).json(e);
        });
        AccountManager accountManager = new AccountManager();
        app.routes(() -> {
            crud("account/:account-id", new AccountController(accountManager));
            post("transaction/", new TransactionController(accountManager));
        });
    }

    public void destroy(){
        app.stop();
    }

    public static void main(String[] args) {
     new BankApp().init();
    }
}
