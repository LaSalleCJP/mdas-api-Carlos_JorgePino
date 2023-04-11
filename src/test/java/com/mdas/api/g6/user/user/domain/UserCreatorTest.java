package com.mdas.api.g6.user.user.domain;

import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.services.UserCreator;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCreatorTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserCreator userCreator;

    @Test
    void shouldCreateUserSuccessfully() throws UserAlreadyExistsException {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        when(userRepositoryMock.existsById(userId)).thenReturn(false);

        // WHEN
        User createdUser = userCreator.create(userId, userName);

        // THEN
        assertEquals(createdUser.getId(), userId);
        assertEquals(createdUser.getName(), userName);
        assertTrue(createdUser.getFavoritePokemons().getFavoritePokemons().isEmpty());
        verify(userRepositoryMock, times(1)).existsById(userId);
        verify(userRepositoryMock, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void shouldThrowUserAlreadyExistsExceptionWhenCreatingUserWithExistingName() {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        when(userRepositoryMock.existsById(userId)).thenReturn(true);

        // WHEN / THEN
        assertThrows(UserAlreadyExistsException.class, () -> userCreator.create(userId, userName));
        verify(userRepositoryMock, times(1)).existsById(userId);
        verify(userRepositoryMock, never()).save(any(User.class));
    }
}
