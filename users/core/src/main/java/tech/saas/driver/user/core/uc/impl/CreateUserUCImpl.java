package tech.saas.driver.user.core.uc.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.saas.driver.common.core.domain.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;
import tech.saas.driver.user.core.mapper.UserMapper;
import tech.saas.driver.user.core.repository.UserRepository;
import tech.saas.driver.user.core.uc.CreateUserUC;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateUserUCImpl implements CreateUserUC {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void create(UserDomain userDomain) {
        UserEntity userEntity = userMapper.toEntity(userDomain);
        userRepository.save(userEntity);
    }
}
