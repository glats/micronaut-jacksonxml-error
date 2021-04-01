package me.jics;

import io.micronaut.http.annotation.*;
import javax.validation.Valid;


@Controller("/user")
public class UserController {
    @Get("/{username}/{firstName}/{lastName}")
    public User getUser(@Valid @RequestBean User user) {
        return user;
    }
}