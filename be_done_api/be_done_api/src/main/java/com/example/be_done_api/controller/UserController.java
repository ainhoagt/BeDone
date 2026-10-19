package com.example.be_done_api.controller;

import com.example.be_done_api.dto.CreateRequestUser;
import com.example.be_done_api.dto.LoginDTO;
import com.example.be_done_api.dto.UpdateUserRequest;
import com.example.be_done_api.dto.UserDTO;
import com.example.be_done_api.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.hasUserWithEmail(email));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.hasUserWithName(name));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> getUserByLogin(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.checkCredentials(loginDTO.getEmail(), loginDTO.getPassword()));
    }
    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateRequestUser user) {
        UserDTO userDTO = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(userDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserRequest user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
