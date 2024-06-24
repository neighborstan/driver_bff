package tech.saas.driver.user.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;
import tech.saas.driver.user.core.mapper.UserEntityMapper;
import tech.saas.driver.user.core.repository.UserPersistanceAdapter;

@Repository
@RequiredArgsConstructor
class UserJpaPersistanceAdapter implements UserPersistanceAdapter {

    private final UserEntityMapper userEntityMapper;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public UserDomain save(UserDomain userDomain) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomain);
        UserEntity save = userJpaRepository.save(userEntity);
        return userEntityMapper.fromEntity(save);
    }

    @Override
    public Iterable<UserDomain> findAll() {
        return userEntityMapper.fromEntityList(userJpaRepository.findAll());
    }
}
