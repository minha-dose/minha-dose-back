package com.minhadose.demo.service;

import org.springframework.stereotype.Service;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.dto.CreateUserDTO;
import com.minhadose.demo.mapper.UserMapper;
import com.minhadose.demo.model.UserModel;
import com.minhadose.demo.repository.AddressRepository;
import com.minhadose.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userMapper = userMapper;

    }

    public Long createUser(CreateUserDTO createUserDTO) {
        UserModel userModel = userMapper.toUserModel(createUserDTO);
        UserModel model = userRepository.save(userModel);
        return model.getUserId();
    }

    public void createUser(CreateUserDTO createUserDTO, AddressDto addressDTO) {
        // Logic to create a user

    }

    public void updateUser() {
        // Logic to update a user
    }

    public void deleteUser() {
        // Logic to delete a user
    }

    public void findUser() {
        // Logic to find a user
    }

}
