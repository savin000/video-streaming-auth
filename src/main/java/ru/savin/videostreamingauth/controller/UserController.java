package ru.savin.videostreamingauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import ru.savin.videostreamingauth.constant.ControllerConstant;
import ru.savin.videostreamingauth.entity.User;
import ru.savin.videostreamingauth.service.UserService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstant.ROOT_PATH + "users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public User getUser(@PathVariable String username) {
        return this.userService.getUserByUsername(username);
    }

    @PostMapping()
    public void addUser(@RequestBody User user) {
        this.userService.addUser(user);
    }

    @DeleteMapping(value = "/{username}", produces = "application/json")
    public void deleteUser(@PathVariable String username) {
        this.userService.deleteUserByUsername(username);
    }
}
