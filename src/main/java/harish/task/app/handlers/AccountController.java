package harish.task.app.handlers;

import harish.task.app.repository.AccountRepository;
import harish.task.app.model.Account;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

import java.util.Optional;

public class AccountController implements CrudHandler{
    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(Context ctx) {
        Double initBalance = Double.valueOf(ctx.req.getParameter("balance"));
        accountRepository.save(new Account(initBalance));
        ctx.status(201);
    }

    @Override
    public void delete(Context ctx, String id) {
        accountRepository.delete(id);
        ctx.status(204);
    }

    @Override
    public void getAll(Context ctx) {
        ctx.json(accountRepository.findAll());
    }

    @Override
    public void getOne(Context ctx, String id) {
        Optional<Account> Account = accountRepository.findById(Long.valueOf(id));
        handleOptionalResponse(ctx, Account);
    }


    private void handleOptionalResponse(Context ctx, Optional<Account> account) {
        account.map(ctx::json)
                .orElse(ctx.status(404));
    }

    @Override
    public void update(Context ctx, String id) {
        ctx.status(204);
    }

}
