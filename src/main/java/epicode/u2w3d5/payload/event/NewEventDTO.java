package epicode.u2w3d5.payload.event;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEventDTO(
        @NotEmpty(message = "title required!")
        String title,

        @NotEmpty(message = "description required!")
        String description,

        @NotEmpty(message = "location required!")
        String location,

        @NotNull(message = "date required! es: 2000-01-01")
        LocalDate date,

        @Min(value = 1, message = "Numeber of max occupation required!")
        int maxOccupation
) {
}
