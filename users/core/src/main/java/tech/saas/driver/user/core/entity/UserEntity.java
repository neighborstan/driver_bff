package tech.saas.driver.user.core.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.scaffold.persistance.IEntity;

@Data
@Table("users")
public class UserEntity implements IEntity {

    @Id
    @Column("user_uid")
    private String userUID;

    @Column("user_full_name")
    private String userFullName;

    @Column("role")
    private String role;

    @Column("phone_number")
    private String phoneNumber;

    @Column("access")
    private boolean access;

    @Column("deleted")
    private boolean deleted;

    @Column("system_source")
    private SystemSource systemSource;
}
