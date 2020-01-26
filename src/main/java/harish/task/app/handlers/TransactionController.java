package harish.task.app.handlers;

import harish.task.app.repository.TransactionRepository;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class TransactionController implements CrudHandler {
    public TransactionController(TransactionRepository transactionRepository) {
    }

    @Override
    public void create(@NotNull Context context) {

    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void getAll(@NotNull Context context) {

    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {

    }
}
