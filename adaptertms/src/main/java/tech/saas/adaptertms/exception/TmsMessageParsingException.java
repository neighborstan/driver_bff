package tech.saas.adaptertms.exception;


/**
 * Исключение, возникающее при проблеме в обработке сообщений из TMS.
 */
public class TmsMessageParsingException  extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public TmsMessageParsingException(String message) {
        super(message);
    }
}
