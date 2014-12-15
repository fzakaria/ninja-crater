package ninja.exceptions;


import ninja.Result;

public class NotFoundException extends NinjaException {

    final static String DEFAULT_MESSAGE = "The requested resource was not found.";

    public NotFoundException() {
        super(Result.SC_404_NOT_FOUND, DEFAULT_MESSAGE);
    }

    public NotFoundException(String message) {
        super(Result.SC_404_NOT_FOUND, message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(Result.SC_404_NOT_FOUND, message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(Result.SC_404_NOT_FOUND, DEFAULT_MESSAGE, cause);
    }
}
