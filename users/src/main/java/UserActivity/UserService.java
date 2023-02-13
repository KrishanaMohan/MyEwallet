package UserActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service

public class UserService {
    @Autowired
    RedisTemplate<String,UserInfo> redisTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public String createUser(UserRequestDto userRequestDto){
        UserInfo userInfo=UserInfo.builder().userName(userRequestDto.getUserName()).age(userRequestDto.getAge())
                .mobileNo(userRequestDto.getMobileNo()).build();
        userRepository.save(userInfo);

        saveInCache(userInfo);

        //send on update to the wallet module/wallet service--> that crete new wallet form userName as message
        kafkaTemplate.send("create_wallet",userInfo.getUserName());

        return "User has been create successfully";

    }
    public void saveInCache(UserInfo userInfo){
        Map map=objectMapper.convertValue(userInfo, Map.class);
        String key="USER_KEY"+userInfo.getUserName();
        redisTemplate.opsForHash().putAll(key,map);
        redisTemplate.expire(userInfo.getUserName(), Duration.ofDays(2));
    }
    public UserInfo findUserByName(String userName){

        Map map=redisTemplate.opsForHash().entries(userName);
        UserInfo userInfo=null;
        if(map==null){
            userInfo=userRepository.findByUserName(userName);
            saveInCache(userInfo);
        }
        else{
            userInfo=objectMapper.convertValue(map,UserInfo.class);

        }
        return userInfo;
    }
}