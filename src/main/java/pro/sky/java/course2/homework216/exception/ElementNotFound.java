package pro.sky.java.course2.homework216.exception;

public class ElementNotFound extends RuntimeException {
    public ElementNotFound() {
    }

    public ElementNotFound(String message) {
        super(message);
    }

    public ElementNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFound(Throwable cause) {
        super(cause);
    }

    public ElementNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
