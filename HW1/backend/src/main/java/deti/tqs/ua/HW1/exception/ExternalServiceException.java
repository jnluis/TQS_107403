package deti.tqs.ua.HW1.exception;

public class ExternalServiceException extends Exception {
    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalServiceException(String message) {
        super(message);
    }
}