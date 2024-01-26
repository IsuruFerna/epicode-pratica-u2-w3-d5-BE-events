package epicode.u2w3d5.service;

import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.NotFoundException;
import epicode.u2w3d5.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Page<User> getUsers(int page, int size, String orderBy) {
        // return usersDAO.findAll();
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy)); // Di default l'ordine è ascendente
        // Se volessimo cambiare l'ordine si usa Sort.Direction.DESC
        // Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        return userDAO.findAll(pageable);
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

    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " not found!"));
    }
}
