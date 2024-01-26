package epicode.u2w3d5.controllers;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.exceptions.BadRequestException;
import epicode.u2w3d5.payload.event.NewEventDTO;
import epicode.u2w3d5.payload.event.NewEventResponse;
import epicode.u2w3d5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public NewEventResponse saveEvent(@RequestBody @Validated NewEventDTO body, BindingResult validation) {
        if(validation.hasErrors()) {
            System.out.println("-----------------------end erros");
            throw new BadRequestException(validation.getAllErrors());
        }

        Event event = eventService.save(body);
        return new NewEventResponse(event.getId());
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Event findById(@PathVariable UUID eventId) {
        return eventService.findById(eventId);
    }

    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event findAndUpdate(@PathVariable UUID eventID, @RequestBody Event body) {
        return eventService.findByIdAndUpdate(eventID, body);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID eventId) {
        eventService.findByIdAndDelete(eventId);
    }

    @PatchMapping("/{eventId}/image")
    @ResponseStatus(HttpStatus.OK)
    public Event uploadImage(@RequestParam("image")MultipartFile file, @PathVariable UUID eventId) {
        try {
            return eventService.uploadImage(eventId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
