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
    ZonedDateTime dateTime;

    @Column
    Double amount;

}
