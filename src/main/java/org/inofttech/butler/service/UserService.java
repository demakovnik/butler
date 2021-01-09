package org.inofttech.butler.service;



import org.inofttech.butler.entity.User;
import org.inofttech.butler.entity.to.UserDto;
import org.inofttech.butler.exception.UserAlreadyExistException;

import java.util.List;

public interface UserService {
    User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistException;

    public List<User> getAllUsers();

    public User getgetUserByName(String username);

    public void saveUser(User user);

    public User getUserById(int id);

    void deleteUserById(int id);
}
