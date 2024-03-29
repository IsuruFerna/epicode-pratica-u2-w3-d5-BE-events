package epicode.u2w3d5.controllers;

import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.BadRequestException;
import epicode.u2w3d5.payload.user.NewUserDTO;
import epicode.u2w3d5.payload.user.NewUserResponseDTO;
import epicode.u2w3d5.payload.user.UserLoginDTO;
import epicode.u2w3d5.payload.user.UserLoginResponseDTO;
import epicode.u2w3d5.service.AuthService;
import epicode.u2w3d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("There are errors in payload!");
        } else  {
            User newUser = authService.save(newUserPayload);

            return new NewUserResponseDTO(newUser.getId());
        }
    }
}
