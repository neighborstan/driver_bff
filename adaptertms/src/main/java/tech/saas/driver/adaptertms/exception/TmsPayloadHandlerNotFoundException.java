package tech.saas.driver.adaptertms.exception;


/**
 * Исключение, возникающее при поиске обработчика сущности из TMS.
 */
public class TmsPayloadHandlerNotFoundException extends RuntimeException {

    public TmsPayloadHandlerNotFoundException(String message) {
        super(message);
    }
}
