package harish.task.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    @Column
    Double balance;

    @OneToMany(targetEntity = AccountTransaction.class, mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<AccountTransaction> transactions;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<AccountTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<AccountTransaction> transactions) {
        this.transactions = transactions;
    }

    public Account(Double initialBalance) {
        balance = initialBalance;
        transactions = new HashSet<>();
    }

    public Account(){
        this(0.00);
    }

    public void addBalance(Double amount){
        balance+=amount;
    }

    public void reduceBalance(Double amount){
        balance-=amount;
    }

    public Long getId() {
        return id;
    }
}
