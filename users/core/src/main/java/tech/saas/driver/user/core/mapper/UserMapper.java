package tech.saas.driver.user.core.mapper;

import org.mapstruct.Mapper;
import tech.saas.driver.common.core.domain.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserEntity toEntity(UserDomain userDomain);
}
