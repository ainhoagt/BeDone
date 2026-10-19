package com.example.be_done_api.service;

import com.example.be_done_api.dto.CreateRequestUser;
import com.example.be_done_api.dto.UpdateUserRequest;
import com.example.be_done_api.dto.UserDTO;
import com.example.be_done_api.exception.NotFound;
import com.example.be_done_api.exception.WrongCredentials;
import com.example.be_done_api.mapper.Mapper;
import com.example.be_done_api.model.User;
import com.example.be_done_api.repository.UserRepository;
import com.example.be_done_api.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id).stream().map(Mapper::toUserDTO).findFirst().orElse(null);
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(Mapper::toUserDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public UserDTO hasUserWithEmail(String email) {
        return userRepository.findByEmail(email).stream().map(Mapper::toUserDTO).findFirst().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO hasUserWithName(String name) {
        return userRepository.findByUserName(name).stream().map(Mapper::toUserDTO).findFirst().orElse(null);
    }
    @Transactional(readOnly = true)
    @Override
    public UserDTO checkCredentials(String email, String password) {
        User user = userRepository.findByEmail(email).stream().findFirst().orElse(null);
        if( user!=null && PasswordUtils.checkPassword(password, user.getPasswordHash())){
            return Mapper.toUserDTO(user);
        }else{
            throw new WrongCredentials("Wrong credentials for user with email "+email);
        }
    }
    @Transactional
    @Override
    public UserDTO createUser(CreateRequestUser user) {
        return Mapper.toUserDTO(userRepository.save(Mapper.toEntity(user)));
    }
    @Transactional
    @Override
    public UserDTO updateUser(Integer id,UpdateUserRequest user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if(userToUpdate!=null){
            if(user.getName()!=null){
                userToUpdate.setUserName(user.getName());
            }
            if(user.getEmail()!=null){
                userToUpdate.setEmail(user.getEmail());
            }
            if(user.getPassword()!=null){
                userToUpdate.setPasswordHash(PasswordUtils.hashPassword(user.getPassword()));
            }
            return Mapper.toUserDTO(userToUpdate);
        }else{
            throw new NotFound("User with id "+id+" not found");
        }
    }
    @Transactional
    @Override
    public void deleteUser(Integer id) {
        User user=userRepository.findById(id).orElse(null);
        if(user!=null) {
            userRepository.delete(user);
        }else{
            throw new NotFound("User with id "+id+" not found");
        }

    }
}
