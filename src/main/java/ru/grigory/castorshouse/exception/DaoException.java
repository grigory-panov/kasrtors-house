package ru.grigory.castorshouse.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.08.14
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */
public class DaoException extends RuntimeException {

    public DaoException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DaoException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DaoException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
