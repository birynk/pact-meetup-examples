package cdct.dummyprovider.controllers;

import cdct.dummyprovider.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/api")
public class UserController {

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return Arrays.asList(
                new User("123", "Max", "Musterman"),
                new User("789", "TestFirstName", "TestLastName")
        );
    }
}
