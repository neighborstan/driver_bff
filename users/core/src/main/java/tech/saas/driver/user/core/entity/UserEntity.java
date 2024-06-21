package tech.saas.driver.user.core.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import tech.saas.driver.common.SystemSource;

@Data
@Table("users")
public class UserEntity {

    @Id
    private String userUID;
    private String userFullName;
    private String role;
    private String phoneNumber;
    private boolean access;
    private boolean deleted;
    private SystemSource systemSource;
}
