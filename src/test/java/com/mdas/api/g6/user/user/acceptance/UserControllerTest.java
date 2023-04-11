package com.mdas.api.g6.user.user.acceptance;

import com.mdas.api.g6.shared.infrastructure.controller.dto.ApiResponse;
import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.infrastructure.controller.dto.CreateUserRequest;
import com.mdas.api.g6.user.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.user.objectmother.UserNameMother;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateUser() {
        // GIVEN
        CreateUserRequest createUserRequest = new CreateUserRequest(
                UserIdMother.random().getId(), UserNameMother.random().getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUserRequest> request = new HttpEntity<>(createUserRequest, headers);

        // WHEN
        ResponseEntity<ApiResponse<User>> response = restTemplate.exchange(
                "/user/create",
                HttpMethod.POST,
                request,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, User.class))
        );
        // THEN
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getMessage());
        assertNotNull(response.getBody().getData());
        assertEquals(createUserRequest.getId(), response.getBody().getData().getId().getId());
    }

    @Test
    public void testCreateUserWithExistingName() {
        // GIVEN
        CreateUserRequest createUserRequest = new CreateUserRequest(
                UserIdMother.random().getId(), UserNameMother.random().getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUserRequest> request = new HttpEntity<>(createUserRequest, headers);

        // WHEN
        ResponseEntity<ApiResponse<User>> response0 = restTemplate.exchange(
                "/user/create",
                HttpMethod.POST,
                request,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, User.class))
        );

        ResponseEntity<ApiResponse<User>> response1 = restTemplate.exchange(
                "/user/create",
                HttpMethod.POST,
                request,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, User.class))
        );

        // THEN
        assertEquals(HttpStatus.CONFLICT, response1.getStatusCode());
        assertNotNull(response1.getBody());
        assertEquals("User already exists with id: " + createUserRequest.getId(), response1.getBody().getMessage());
        assertNull(response1.getBody().getData());
    }

}

