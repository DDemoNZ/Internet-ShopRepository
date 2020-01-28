package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {

    Optional<User> getByUsername(String username) throws DataProcessingException;

    Optional<User> getByToken(String token) throws DataProcessingException;

    Optional<User> login(String username) throws DataProcessingException;
}
