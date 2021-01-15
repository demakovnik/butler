package org.inofttech.butler.service;


import org.inofttech.butler.dao.UserRepository;
import org.inofttech.butler.entity.Role;
import org.inofttech.butler.entity.User;
import org.inofttech.butler.entity.to.UserDto;
import org.inofttech.butler.exception.NoSuchItemException;
import org.inofttech.butler.exception.UserAlreadyExistException;
import org.inofttech.butler.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends AbstractService<User, UserRepository> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        super(repository);
    }



    public User save(UserDto userDto) throws UserAlreadyExistException {
        User user = null;
        String username = userDto.getUsername();
        if (!isUserExist(username)) {
            user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
        }
        return repository.save(user);
    }

    @Override
    public List<User> getAll() {

        return repository.findAll();
    }

    @Override
    public void save(User user) {
        String username = user.getUsername();
        if (!isUserExist(username)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            repository.save(user);
        }
    }

    @Override
    public User getById(long id) {
        User user = null;
        Optional<User> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new NoSuchItemException("There is no user with ID=" + id
                    + " in database");
        }
        return optional.get();
    }


    @Override
    public void deleteById(long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchItemException("There is no User with ID= " + id + " in database");

        }
        repository.deleteById( id);
    }


    public User getUserByName(String username) {
        return repository.findByUsername(username);
    }

    private boolean isUserExist(String username) {
        User byUsername = repository.findByUsername(username);
        if (byUsername != null) {
            throw new UserAlreadyExistException("User with username " +
                    username + " is already exists in database");
        }
        return false;
    }
}
