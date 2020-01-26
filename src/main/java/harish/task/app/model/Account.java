package harish.task.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name="accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    @Column
    Double balance;

    @OneToMany
    Set<AccountTransaction> transactions;

    public Account(Double initialBalance) {
        balance = initialBalance;
        transactions = Collections.emptySet();
    }

    public void addBalance(Double amount){
        balance+=amount;
    }

    public void reduceBalance(Double amount){
        balance-=amount;
    }
}
