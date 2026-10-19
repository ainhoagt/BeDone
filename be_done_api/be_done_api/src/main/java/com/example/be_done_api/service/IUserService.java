package com.example.be_done_api.service;

import com.example.be_done_api.dto.CreateRequestUser;
import com.example.be_done_api.dto.UpdateUserRequest;
import com.example.be_done_api.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO hasUserWithEmail(String email);
    UserDTO hasUserWithName(String name);
    UserDTO checkCredentials(String email, String password);
    UserDTO createUser(CreateRequestUser user);
    UserDTO updateUser(Integer id, UpdateUserRequest user);
    void deleteUser(Integer id);
}
