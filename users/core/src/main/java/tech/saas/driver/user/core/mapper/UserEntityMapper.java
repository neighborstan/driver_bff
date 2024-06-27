package tech.saas.driver.user.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.common.scaffold.persistence.IEntityMapper;
import tech.saas.driver.user.core.entity.UserEntity;

@Mapper
public interface UserEntityMapper extends IEntityMapper<UserDomain, UserEntity> {

    @Override
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDomain domain);
}
