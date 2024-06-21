package tech.saas.driver.common.core.domain;

import lombok.Data;
import tech.saas.driver.common.SystemSource;

/**
 *  Сущность пользователя для работы с очередью
 */
@Data
public class UserDomain {

    private String userUID;
    private String userFullName;
    private String role;
    private String phoneNumber;
    private boolean access;
    private boolean deleted;
    private SystemSource systemSource;
}
