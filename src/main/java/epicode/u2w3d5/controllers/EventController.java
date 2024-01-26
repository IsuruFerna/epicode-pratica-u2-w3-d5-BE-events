package epicode.u2w3d5.controllers;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }
}
