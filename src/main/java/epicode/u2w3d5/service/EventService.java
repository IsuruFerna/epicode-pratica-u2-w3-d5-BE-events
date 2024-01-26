package epicode.u2w3d5.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.NotFoundException;
import epicode.u2w3d5.payload.event.NewEventDTO;
import epicode.u2w3d5.payload.event.NewEventResponse;
import epicode.u2w3d5.repository.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Event save(NewEventDTO body) {
        // later bulid validation base on title
        Event event = new Event();
        event.setDate(body.date());
        event.setDescription(body.description());
        event.setLocation(body.location());
        event.setTitle(body.title());
        event.setMaxOccupation(body.maxOccupation());

        return eventDAO.save(event);
    }

    public List<Event> getEvents() {
        return eventDAO.findAll();
    }

    public Event findById(UUID id) {
        return eventDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Event found = this.findById(id);
        eventDAO.delete(found);
    }

    public Event findByIdAndUpdate(UUID id, Event body) {
        Event found = this.findById(id);
        found.setDate(body.getDate());
        found.setDescription(body.getDescription());
        found.setLocation(body.getLocation());
        found.setMaxOccupation(body.getMaxOccupation());
        found.setTitle(body.getTitle());
        return eventDAO.save(found);
    }

    public Event uploadImage(UUID id, MultipartFile file) throws IOException {
        Event found = this.findById(id);
        String imageURL = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImage(imageURL);
        return eventDAO.save(found);
    }
}
