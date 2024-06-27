package tech.saas.driver.user.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;
import tech.saas.driver.user.core.mapper.UserEntityMapper;
import tech.saas.driver.user.core.repository.UserJdbcRepository;
import tech.saas.driver.user.core.repository.UserPersistenceAdapter;

@Repository
@RequiredArgsConstructor
public class UserJdbcPersistenceAdapter implements UserPersistenceAdapter {

    private final UserEntityMapper userEntityMapper;
    private final UserJdbcRepository userJdbcRepository;

    @Transactional
    public UserDomain save(UserDomain userDomain) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomain);
        UserEntity save = userJdbcRepository.save(userEntity);
        return userEntityMapper.fromEntity(save);
    }

    @Override
    public Iterable<UserDomain> findAll() {
        return userEntityMapper.fromEntities(userJdbcRepository.findAll());
    }
}
