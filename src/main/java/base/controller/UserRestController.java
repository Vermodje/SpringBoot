package base.controller;

import base.exception.UserException;
import base.model.User;
import base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "user", params = {"id"})
    public ResponseEntity<User> getUser(@RequestParam("id") long id) {
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "insert")
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        try {
            userService.add(user);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @DeleteMapping(value = "delete", params = {"id"})
    public ResponseEntity<User> deleteUser(@RequestParam("id") long id) {
        try {
            userService.deleteUser(id);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "update", params = {"id"})
    public ResponseEntity<User> updateUser(@RequestParam("id") long id, @RequestBody User user) {
        User currentUser = null;
        try {
            currentUser = userService.getUserById(id);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.setEmail(user.getEmail());
        currentUser.setLogin(user.getLogin());
        currentUser.setName(user.getName());
        currentUser.setPassword(user.getPassword());
        currentUser.setRoles(user.getRoles());
        try {
            userService.updateUser(currentUser);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
}
