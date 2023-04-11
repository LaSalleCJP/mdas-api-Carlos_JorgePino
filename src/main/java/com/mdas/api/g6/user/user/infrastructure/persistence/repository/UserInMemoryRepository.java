package com.mdas.api.g6.user.user.infrastructure.persistence.repository;

import com.mdas.api.g6.user.user.infrastructure.persistence.entity.UserInMemoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserInMemoryRepository {

    private final Map<Long, UserInMemoryEntity> userMap = new HashMap<>();

    public boolean existsByName(Long userId) {
        return userMap.containsKey(userId);
    }

    public void save(UserInMemoryEntity userInMemoryEntity) {
        userMap.put(userInMemoryEntity.getId(), userInMemoryEntity);
    }

    public UserInMemoryEntity getUserById(Long userId) {
        return userMap.get(userId);
    }

    public void update(UserInMemoryEntity user) {
        userMap.put(user.getId(), user);
    }

}
