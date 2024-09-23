package com.hrm.human.resource.management.system.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    EMPLOYEE_READ("customer:read"),
    EMPLOYEE_UPDATE("customer:update"),
    EMPLOYEE_CREATE("customer:create"),
    EMPLOYEE_DELETE("customer:delete"),
    ;

    @Getter
    private final String permission;
}
