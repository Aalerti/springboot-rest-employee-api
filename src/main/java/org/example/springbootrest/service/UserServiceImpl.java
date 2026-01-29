package org.example.springbootrest.service;

import org.example.springbootrest.dao.UserRepository;
import org.example.springbootrest.dto.UserDTO;
import org.example.springbootrest.exception_handling.UserExistsWithThatUsername;
import org.example.springbootrest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.springbootrest.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Users name don't exist"));
        return userMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserExistsWithThatUsername("Username already exists");
        }
        User user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("EMPLOYEE");

        userRepository.save(user);
    }
}
