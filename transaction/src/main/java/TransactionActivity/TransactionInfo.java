package TransactionActivity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromUser;
    private String toUser;
    private int transactionAmount;
    private String transactionId ;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Date tansactionDate;
    private String purpose;

    public void setTransactionStatus(TransactionActivity.TransactionStatus pending) {
    }
}
