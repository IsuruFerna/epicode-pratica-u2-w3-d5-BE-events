package epicode.u2w3d5.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ErrorsPayload {
    private String message;
    private String timestamp;
}
