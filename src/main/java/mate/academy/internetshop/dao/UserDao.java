package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> getByUsername(String username);

    Optional<User> getByToken(String token);

    User login(String username, String password) throws AuthenticationException;
}
