package ru.grigory.castorshouse.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 27.09.14
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
