package tech.saas.driver.user.core.repository;

import org.springframework.data.repository.CrudRepository;
import tech.saas.driver.user.core.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
