package harish.task.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class AccountTransaction implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    @Column
    UUID transactionId;

    @Column
    Long belongsTo;

    @Column
    ZonedDateTime dateTime;

    @Column
    Double amount;

    public AccountTransaction(Long id, UUID transactionLink, ZonedDateTime transactionTime, Double amount) {
        this.belongsTo = id;
        this.transactionId = transactionLink;
        this.dateTime = transactionTime;
        this.amount=amount;
    }

    public Long getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Long belongsTo) {
        this.belongsTo = belongsTo;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public Double getAmount() {
        return amount;
    }

    public AccountTransaction(){

    }

}
