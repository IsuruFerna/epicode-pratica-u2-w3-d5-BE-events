package epicode.u2w3d5.service;

import epicode.u2w3d5.entities.Role;
import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.BadRequestException;
import epicode.u2w3d5.exceptions.UnauthorizedException;
import epicode.u2w3d5.payload.user.NewUserDTO;
import epicode.u2w3d5.payload.user.NewUserResponseDTO;
import epicode.u2w3d5.payload.user.UserLoginDTO;
import epicode.u2w3d5.repository.UserDAO;
import epicode.u2w3d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        User user = userService.findByEmail(body.email());

        if(bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Not valid credentials!");
        }

    }

    public User save(NewUserDTO user) {
        userDAO.findByEmail(user.email()).ifPresent(found -> {
            throw new BadRequestException("Email " + found.getEmail() + " is already exsist!");
        });
        User u = new User();
        u.setEmail(user.email());
        u.setFirstname(user.firstName());
        u.setSurname(user.surname());
        u.setPassword(bcrypt.encode(user.password()));
        u.setRole(Role.USER);
        return userDAO.save(u);
    }

}
