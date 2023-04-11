package com.mdas.api.g6.user.user.domain.repository;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;

import java.util.UUID;

public interface UserRepository {
    boolean existsById(UserId userId);
    void save(User user);
    User getUserById(UserId userId);
    void update(User user);
}
