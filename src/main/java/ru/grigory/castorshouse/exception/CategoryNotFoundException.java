package ru.grigory.castorshouse.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 27.09.14
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public CategoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
