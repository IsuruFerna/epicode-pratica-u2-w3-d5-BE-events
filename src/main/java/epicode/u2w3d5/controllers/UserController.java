package epicode.u2w3d5.controllers;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.entities.User;
import epicode.u2w3d5.exceptions.NotFoundException;
import epicode.u2w3d5.payload.user.UserResponseDTO;
import epicode.u2w3d5.repository.EventDAO;
import epicode.u2w3d5.service.EventService;
import epicode.u2w3d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDAO eventDAO;


    // **************** ADMIN ACCESS ********************

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy) {
        return userService.getUsers(page, size, orderBy);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.findById(userId);
    }


    // **************** USER SELF ACCESS ********************

    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentUser, @RequestBody User body) {
        return userService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    public void getMeAnDelete(@AuthenticationPrincipal User currentUser) {
        userService.findByIdAndDelete(currentUser.getId());
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser)
    {
        User user = userService.findById(currentUser.getId());
        return currentUser;
    }

    // *************** EVENTS ***************
    @GetMapping("/me/events")
    public List<Event> getProfileEvents(@AuthenticationPrincipal User currentUser)
    {
        return eventDAO.userEvents(currentUser.getId());
    }

    @DeleteMapping("/me/events/{eventId}")
    public void deleteProfileEvents(@AuthenticationPrincipal User currentUser, @PathVariable UUID eventId)
    {
        userService.deleteUserEventByEventId(currentUser, eventId);
    }

    @PostMapping("/me/events/{eventId}")
    public List<Event> postProfileEvent(@AuthenticationPrincipal User currentUser, @PathVariable UUID eventId)
    {
        return userService.addEvent(currentUser, eventId);
    }

}
