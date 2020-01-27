package harish.task.app.handlers;

import harish.task.app.dto.AccountListResponse;
import harish.task.app.dto.AccountRequest;
import harish.task.app.dto.AccountResponse;
import harish.task.app.dto.TransactionDto;
import harish.task.app.model.AccountTransaction;
import harish.task.app.dao.AccountManager;
import harish.task.app.model.Account;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountController implements CrudHandler{
    private AccountManager accountManager;

    public AccountController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public void create(Context ctx) {
        AccountRequest accountRequest = ctx.bodyAsClass(AccountRequest.class);
        Long accountId = accountManager.save(new Account(accountRequest.getBalance()));
        ctx.status(201).header("Location","account/"+accountId);
    }

    @Override
    public void delete(Context ctx, String id) {
        accountManager.delete(Long.valueOf(id));
        ctx.status(204);
    }

    @Override
    public void getAll(Context ctx) {
        List<AccountResponse> accountsDto = accountManager.findAll().stream().map(this::toAccountDto).collect(Collectors.toList());
        AccountListResponse response = new AccountListResponse(accountsDto);
        ctx.json(response);
    }

    private AccountResponse toAccountDto(Account account) {
        List<TransactionDto> transactionDtos = account.getTransactions().stream().map(this::toTransactionDto).collect(Collectors.toList());
        AccountResponse accountResponse = new AccountResponse(account.getId(),account.getBalance(),transactionDtos);
        return accountResponse;
    }

    private TransactionDto toTransactionDto(AccountTransaction accountTransaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(accountTransaction.getAmount());
        transactionDto.setTimestamp(accountTransaction.getDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        transactionDto.setTransactionUUID(accountTransaction.getTransactionId().toString());
        return transactionDto;
    }

    @Override
    public void getOne(Context ctx, String id) {
        Optional<Account> account = accountManager.findById(Long.valueOf(id));
        account.map(this::toAccountDto).map(ctx::json);
    }



    @Override
    public void update(Context ctx, String id) {
        ctx.status(204);
    }

}
