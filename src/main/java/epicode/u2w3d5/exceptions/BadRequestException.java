package epicode.u2w3d5.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorList;

    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(List<ObjectError> errors) {
        this.errorList = errors;
    }
}
