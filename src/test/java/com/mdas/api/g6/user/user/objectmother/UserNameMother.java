package com.mdas.api.g6.user.user.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;

public class UserNameMother {

    private static final Faker faker = new Faker();

    public static UserName random() {
        String name = faker.name().username();
        return new UserName(name);
    }

    public static UserName random(String name) {
        return new UserName(name);
    }
}
