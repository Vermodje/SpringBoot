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
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers()  {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/user", params = {"id"},method = RequestMethod.GET)
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

    @RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        try {
            userService.add(user);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/admin/delete", params = {"id"}, method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@RequestParam("id") long id) {
        try {
            userService.deleteUser(id);
        } catch (UserException e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/admin/update",  params = {"id"}, method = RequestMethod.PUT)
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