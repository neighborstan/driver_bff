package tech.saas.driver.user.core.exception;

public class KeycloakRoleRepresentationException extends UsersException {

    public KeycloakRoleRepresentationException(String message) {
        super(message);
    }

    public KeycloakRoleRepresentationException(String message, String error) {
        super(message, error);
    }
}
