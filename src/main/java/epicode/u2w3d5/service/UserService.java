package epicode.u2w3d5.service;

import epicode.u2w3d5.entities.Role;
import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.BadRequestException;
import epicode.u2w3d5.exceptions.NotFoundException;
import epicode.u2w3d5.payload.user.NewUserDTO;
import epicode.u2w3d5.payload.user.NewUserResponse;
import epicode.u2w3d5.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.findAll();
    }

    public NewUserResponse save(NewUserDTO user) {
        userDAO.findByEmail(user.email()).ifPresent(found -> {
            throw new BadRequestException("Email " + found.getEmail() + " is already exsist!");
        });
        User u = new User();
        u.setEmail(user.email());
        u.setFirstname(user.firstName());
        u.setSurname(user.lastName());
        u.setPassword(user.password());
        u.setRole(Role.USER);
        return new NewUserResponse(u.getId());
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByIdAndUpdate(UUID id, User body) {
        User found = this.findById(id);
        found.setFirstname(body.getFirstname());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());

        return userDAO.save(found);
    }
}
