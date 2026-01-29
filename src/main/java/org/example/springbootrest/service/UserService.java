package org.example.springbootrest.service;

import org.example.springbootrest.dto.UserDTO;

public interface UserService {

    UserDTO findByUsername(String username);

    void register(UserDTO userDTO);
}
