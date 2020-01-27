package harish.task.app.dto;

import java.util.List;

public class AccountListResponse {
    private List<AccountResponse> accounts;
    public AccountListResponse(List<AccountResponse> all) {
        this.accounts = all;
    }

    public List<AccountResponse> getAccounts() {
        return accounts;
    }
}
