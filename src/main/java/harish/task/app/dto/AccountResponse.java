package harish.task.app.dto;

import java.util.List;

public class AccountResponse {
    private Long accountId;
    private Double balance;
    private List<TransactionDto> transactions;

    public AccountResponse(Long id,Double balance, List<TransactionDto> transactions) {
        this.accountId=id;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
