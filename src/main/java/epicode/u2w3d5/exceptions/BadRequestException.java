package epicode.u2w3d5.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorsList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> errorsList) {
        super("Errors are in the body");
        this.errorsList = errorsList;
    }
}
