package com.mdas.api.g6.user.user.application;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.services.UserCreator;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.user.objectmother.FavoritePokemonsMother;
import com.mdas.api.g6.user.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.user.objectmother.UserMother;
import com.mdas.api.g6.user.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserCreator userCreatorMock;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    void shouldCreateUserSuccessfully() throws UserAlreadyExistsException {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        FavoritePokemons favoritePokemons = FavoritePokemonsMother.random(new HashSet<>());
        User user = UserMother.random(userId, userName, new FavoritePokemons(new HashSet<>()));
        when(userCreatorMock.create(any(UserId.class), any(UserName.class))).thenReturn(user);

        // WHEN
        User createdUser = createUserUseCase.execute(1L, userName.getName());

        // THEN
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(userName.getName(), createdUser.getName().getName());
        assertTrue(createdUser.getFavoritePokemons().getFavoritePokemons().isEmpty());
        verify(userCreatorMock, times(1)).create(any(UserId.class), any(UserName.class));
    }

    @Test()
    void shouldThrowExceptionWhenUserAlreadyExists() throws UserAlreadyExistsException {
        // Given
        UserName userName = UserNameMother.random();
        UserId userId = UserIdMother.random();
        doThrow(UserAlreadyExistsException.class).when(userCreatorMock).create(any(UserId.class), any(UserName.class));

        // When
        Throwable exception = assertThrows(UserAlreadyExistsException.class, () -> {
            createUserUseCase.execute(userId.getId(), userName.getName());
        });

        // Then
        assertNotNull(exception);
        verify(userCreatorMock, times(1)).create(any(UserId.class), any(UserName.class));
    }
}
