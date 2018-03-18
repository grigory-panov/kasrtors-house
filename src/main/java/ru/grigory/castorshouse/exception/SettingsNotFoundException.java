package ru.grigory.castorshouse.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 18.10.14
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public class SettingsNotFoundException extends DaoException {
    public SettingsNotFoundException() {
    }

    public SettingsNotFoundException(String message) {
        super(message);
    }

    public SettingsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SettingsNotFoundException(Throwable cause) {
        super(cause);
    }

    public SettingsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
