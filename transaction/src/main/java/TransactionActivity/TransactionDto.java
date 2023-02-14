package TransactionActivity;

import lombok.Data;

@Data
public class TransactionDto {

    private String fromUser;
    private String toUser;
    private int amount;
    private String purpose;
}
