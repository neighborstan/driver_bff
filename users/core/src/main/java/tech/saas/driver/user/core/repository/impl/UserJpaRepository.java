package tech.saas.driver.user.core.repository.impl;

import org.springframework.data.repository.CrudRepository;
import tech.saas.driver.user.core.entity.UserEntity;

public interface UserJpaRepository extends CrudRepository<UserEntity, String> {
}
