package com.mdas.api.g6.user.user.infrastructure.controller;

import com.mdas.api.g6.shared.infrastructure.controller.dto.ApiResponse;
import com.mdas.api.g6.user.user.application.AddPokemonFavoriteUseCase;
import com.mdas.api.g6.user.user.application.CreateUserUseCase;
import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.user.infrastructure.controller.dto.AddFavoritePokemonRequest;
import com.mdas.api.g6.user.user.infrastructure.controller.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final AddPokemonFavoriteUseCase addPokemonFavoriteUseCase;

    @PostMapping(value = "/create")
    public ResponseEntity<ApiResponse<User>> create(@RequestBody CreateUserRequest createUserRequest) {
        User user;
        try {
            user = createUserUseCase.execute(createUserRequest.getId(), createUserRequest.getName());
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED, null, user));
    }

    @PostMapping("/add-favorite-pokemon")
    public ResponseEntity<ApiResponse<User>> addFavoritePokemon(
            @RequestHeader("user_id") Long userId,
            @RequestBody AddFavoritePokemonRequest request) {

        User result;

        try {
            result = addPokemonFavoriteUseCase.execute(userId, request);
        } catch( UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), null));
        } catch (PokemonAlreadyAddException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED, null, result));
    }

}
