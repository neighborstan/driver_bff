package tech.saas.driver.user.core.exception;

import lombok.Getter;

@Getter
public class UsersException extends RuntimeException {

    private String error;

        public UsersException(String message) {
            super(message);
        }

        public UsersException(String message, String error) {
            super(message);
            this.error = error;
        }
}
