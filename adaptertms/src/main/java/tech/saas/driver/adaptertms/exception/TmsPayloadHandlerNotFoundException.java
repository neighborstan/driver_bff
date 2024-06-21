package tech.saas.driver.adaptertms.exception;


/**
 * Исключение, возникающее при поиске обработчика сущности из TMS.
 */
public class TmsPayloadHandlerNotFoundException extends AdapterTmsException {

    public TmsPayloadHandlerNotFoundException(String message) {
        super(message);
    }
}
