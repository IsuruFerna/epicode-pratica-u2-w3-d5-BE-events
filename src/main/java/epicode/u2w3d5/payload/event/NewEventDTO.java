package epicode.u2w3d5.payload.event;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewEventDTO(
        @NotEmpty(message = "title required!")
        String title,

        @NotEmpty(message = "description required!")
        String description,

        @NotEmpty(message = "location required!")
        String location,

        @NotEmpty(message = "date required! es: 2000-01-01")
        LocalDate localDate
) {
}
