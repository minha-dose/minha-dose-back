package com.minhadose.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.minhadose.demo.dto.CreateUserDTO;
import com.minhadose.demo.mapper.AddressMapper;
import com.minhadose.demo.mapper.UserMapper;
import com.minhadose.demo.model.AddressModel;
import com.minhadose.demo.model.UserModel;
import com.minhadose.demo.repository.AddressRepository;
import com.minhadose.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, UserMapper userMapper, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    public Long createUser(CreateUserDTO createUserDTO) {
        UserModel userModel = userMapper.toUserModel(createUserDTO);

    if (createUserDTO.address() != null) {
        AddressModel address = addressRepository.save(addressMapper.toDomain(createUserDTO.address()));
        userModel.setAddress(address);
    }

    UserModel savedUser = userRepository.save(userModel);
    return savedUser.getUserId();

    }

    public List<CreateUserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toCreateUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<CreateUserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toCreateUserDTO);
    }

    public CreateUserDTO updateUser(Long id, CreateUserDTO createUserDTO) {
        return userRepository.findById(id).map(existingUser -> {
            UserModel updatedUser = userMapper.toUserModel(createUserDTO);
            updatedUser.setUserId(id); // Garante que não criamos um novo usuário, apenas atualizamos
            return userMapper.toCreateUserDTO(userRepository.save(updatedUser));
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserModel> getUsersByCity(String city) {
        return userRepository.findByAddress_City(city);
    }

    public List<UserModel> getUsersByCep(String cep) {
        return userRepository.findByAddress_ZipCode(cep);
    }

}
