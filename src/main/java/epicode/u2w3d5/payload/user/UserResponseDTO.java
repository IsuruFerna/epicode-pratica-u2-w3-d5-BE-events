package epicode.u2w3d5.payload.user;

import epicode.u2w3d5.entities.Event;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserResponseDTO(
        String firstName,
        String surname,
        String email,
        List<Event> events
) {
}
