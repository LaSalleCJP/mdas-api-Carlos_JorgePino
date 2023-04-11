package com.mdas.api.g6.user.user.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;

public class UserIdMother {

    public static UserId random() {
        return new UserId(Faker.instance().random().nextLong());
    }

    public static UserId random(Long id) {
        return new UserId(id);
    }
}
