package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user) throws DataProcessingException;

    User get(Long userId) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    boolean delete(Long userId) throws DataProcessingException;

    List<User> getAll() throws DataProcessingException;

    User login(String username, String password) throws AuthenticationException,
            DataProcessingException;

    Optional<User> getByToken(String token) throws DataProcessingException;

    boolean checkLogin(String login) throws DataProcessingException;
}
