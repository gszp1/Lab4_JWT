package org.example.zadania_rest_api.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),
    GUEST_READ("guest:read"),
    GUEST_UPDATE("guest:update"),
    GUEST_CREATE("guest:create"),
    GUEST_DELETE("guest:delete");

    private final String permission;

}
