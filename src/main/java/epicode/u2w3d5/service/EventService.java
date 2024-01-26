package epicode.u2w3d5.service;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.NotFoundException;
import epicode.u2w3d5.repository.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    public List<Event> getEvents() {
        return eventDAO.findAll();
    }

    public Event findById(UUID id) {
        return eventDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

}
