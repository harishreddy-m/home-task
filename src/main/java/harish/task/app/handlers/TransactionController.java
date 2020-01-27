package harish.task.app.handlers;

import harish.task.app.dao.AccountManager;
import harish.task.app.dto.TransferRequest;
import harish.task.app.exception.FailedTransaction;
import harish.task.app.model.Account;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class TransactionController implements Handler {
    private AccountManager accountManager;
    public TransactionController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        TransferRequest request = context.bodyAsClass(TransferRequest.class);
        Account fromAccount = accountManager.findById(request.getFromAccount()).orElseThrow(()->new FailedTransaction("From Account does not exist"));
        Account toAccount = accountManager.findById(request.getToAccount()).orElseThrow(()->new FailedTransaction("To account does not exist"));
        accountManager.transfer(fromAccount,toAccount,request.getAmount());
        context.status(204);
    }
}
