package org.inofttech.butler.service;


import org.inofttech.butler.dao.UserRepository;
import org.inofttech.butler.entity.Role;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.entity.to.UserDto;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistException(
                    "There is an account with that login: "
                            + userDto.getUsername());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        String username = user.getUsername();
        User byUsername = userRepository.findByUsername(username);
        if (byUsername != null) {
            throw new UserAlreadyExistException("User with username " +
                    username + " already exists in database");
        }

        userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NoSuchItemException("There is no user with ID=" + id
                    + " in database");
        }
        return optional.get();
    }


    @Override
    public void deleteUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchItemException("There is no User with ID= " + id + " in database");

        }
        userRepository.deleteById(id);
    }

    @Override
    public User getgetUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}