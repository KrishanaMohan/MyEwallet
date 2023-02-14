package TransactionActivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.UUID;



@Service
public class TransactionService {
    @Autowired


    public void createTransaction( TransactionDto transactionDto){

        //First of all we create transaction Entity and put its status to pending

        TransactionInfo transaction=TransactionInfo.builder().fromUser(transactionDto.getFromUser()).toUser(transactionDto.getToUser())
                .tansactionDate(new Date()).transactionAmount(transactionDto.getAmount())
                .purpose(transactionDto.getPurpose()).transactionId(UUID.randomUUID().toString())
                .build();

        transaction.setTransactionStatus(TransactionStatus.PENDING);//becoz unable to set via Builder;

    }
}
