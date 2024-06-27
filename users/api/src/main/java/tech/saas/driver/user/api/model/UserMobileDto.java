package tech.saas.driver.user.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.scaffold.api.IMobileDto;

@Data
public class UserMobileDto implements IMobileDto {

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
