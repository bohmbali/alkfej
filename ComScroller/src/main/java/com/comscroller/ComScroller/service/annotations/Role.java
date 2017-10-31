package com.comscroller.ComScroller.service.annotations;

import com.comscroller.ComScroller.model.Users;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Bőhm Balázs
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
    Users.Role[] value() default {Users.Role.GUEST};
}
