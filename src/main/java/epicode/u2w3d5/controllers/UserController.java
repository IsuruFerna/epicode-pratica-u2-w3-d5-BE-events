package epicode.u2w3d5.controllers;

import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
