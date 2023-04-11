package com.mdas.api.g6.user.user.application;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.services.UserCreator;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCase {
    private UserCreator userCreator;

    public User execute(Long id, String name) throws UserAlreadyExistsException {
        return userCreator.create(new UserId(id), new UserName(name));
    }
}