package UserActivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String createUser(@RequestBody UserRequestDto userRequestDto){
        return  userService.createUser(userRequestDto);
    }
    @GetMapping("/findByName")
    public UserInfo findUser(@RequestParam String userName){
        return userService.findUserByName(userName);
    }
}