package epicode.u2w3d5.service;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.NotFoundException;
import epicode.u2w3d5.payload.user.UserResponseDTO;
import epicode.u2w3d5.repository.EventDAO;
import epicode.u2w3d5.repository.UserDAO;
import org.hibernate.Hibernate;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
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

//    @Autowired
//    private EventService eventService;

    @Autowired
    private EventDAO eventDAO;

    public Page<User> getUsers(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userDAO.findAll(pageable);
    }


    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Event> addEvent(User user, UUID eventId) {
        User find = this.findById(user.getId());
        Event findEvent = eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
        find.addEvent(findEvent);
        userDAO.save(find);
        return find.getEvents();
    }

    public List<Event> userEvents(User user) {
        User find = this.findById(user.getId());
        return find.getEvents();
    }

    public void deleteUserEventByEventId(User user, UUID eventID) {
//        userDAO.deleteUserEventByEventId(user.getId(), eventID);
        User found = this.findById(user.getId());
        Event foundEvent = eventDAO.findById(eventID).orElseThrow(()-> new NotFoundException(eventID));
        found.getEvents().remove(foundEvent);
        userDAO.save(found);
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
