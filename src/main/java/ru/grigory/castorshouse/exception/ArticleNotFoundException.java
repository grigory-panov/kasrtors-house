package ru.grigory.castorshouse.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 28.09.14
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class ArticleNotFoundException extends BusinessException {
    public ArticleNotFoundException() {
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleNotFoundException(Throwable cause) {
        super(cause);
    }

    public ArticleNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
