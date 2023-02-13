package WallectActivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    KafkaTemplate<String,String>kafkaTemplate;
    @KafkaListener(topics="create_wallet",groupId="test1234")
    public void creteWallet(String message){
        WalletInfo wallet=WalletInfo.builder().balance(100).userName(message).build();
        walletRepository.save(wallet);

    }
}
