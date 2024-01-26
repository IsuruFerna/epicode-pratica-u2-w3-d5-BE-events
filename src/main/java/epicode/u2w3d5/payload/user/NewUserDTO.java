package epicode.u2w3d5.payload.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "first name required!")
        @Size(min = 3, max = 30, message = "Name must contain around 3 to 30 charachters!")
        String firstName,
        @NotEmpty(message = "Last name required!")
        @Size(min = 3, max = 30, message = "Lastname must contain around 3 to 30 charachters!")
        String lastName,
        @Email
        @NotEmpty(message = "email required!")
        String email,

        @NotEmpty(message = "Password required!")
        @Size(min = 5, max= 32, message = "Password must contain around 5 to 32 charachters!")
        String password
) {
}
