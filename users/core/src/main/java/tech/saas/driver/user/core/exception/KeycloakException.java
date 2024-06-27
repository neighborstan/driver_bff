package tech.saas.driver.user.core.exception;

public class KeycloakException extends UsersException {

        public KeycloakException(String message) {
            super(message);
        }

        public KeycloakException(String message, String error) {
            super(message, error);
        }
}
