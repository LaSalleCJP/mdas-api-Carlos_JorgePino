package com.mdas.api.g6.user.user.infrastructure.persistence.adapter;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import com.mdas.api.g6.user.user.infrastructure.persistence.repository.UserInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInMemoryAdapter implements UserRepository {

    private final UserInMemoryRepository userInMemoryRepository;
    private final UserInMemoryMapper userInMemoryMapper;

    @Override
    public boolean existsById(UserId userId) {
        return userInMemoryRepository.existsByName(userId.getId());
    }

    @Override
    public void save(User user) {
      UserInMemoryEntity userInMemoryEntity = userInMemoryMapper.toEntity(user);
      userInMemoryRepository.save(userInMemoryEntity);
    }

    @Override
    public User getUserById(UserId userId) {
        UserInMemoryEntity userInMemoryEntity = userInMemoryRepository.getUserById(userId.getId());
        if (userInMemoryEntity == null) {
            return null;
        }
        return userInMemoryMapper.toDomain(userInMemoryEntity);
    }

    @Override
    public void update(User user) {
        UserInMemoryEntity userInMemoryEntity = userInMemoryMapper.toEntity(user);
        userInMemoryRepository.update(userInMemoryEntity);
    }

}
