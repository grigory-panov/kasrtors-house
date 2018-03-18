package ru.grigory.castorshouse.exception;

/**
 * Created by grigory on 09.11.15.
 */
public class ImageProcessingException extends RuntimeException {
    public ImageProcessingException() {
    }

    public ImageProcessingException(String message) {
        super(message);
    }

    public ImageProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageProcessingException(Throwable cause) {
        super(cause);
    }
}
