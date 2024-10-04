package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = authenticatedUser.getName();
        User userInDB = userService.findByUserName(userName);
        userInDB.setPassword(user.getPassword());
        userInDB.setUsername(user.getUsername());
        userService.saveNewEntry(userInDB);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<User> deleteUser() {
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authenticatedUser.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}