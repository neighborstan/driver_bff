package tech.saas.driver.common.core.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.scaffold.IDomain;

/**
 *  Сущность пользователя для работы с очередью
 */
@Data
public class UserDomain implements IDomain {

    @JsonProperty("userUID")
    private String userUID;

    @JsonProperty("userFullName")
    private String userFullName;

    @JsonProperty("role")
    private String role;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("access")
    private boolean access;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("systemSource")
    private SystemSource systemSource;
}
