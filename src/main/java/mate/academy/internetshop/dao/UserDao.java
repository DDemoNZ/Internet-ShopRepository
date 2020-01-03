package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.model.User;

public interface UserDao {

    User add(User user);

    Optional<User> get(Long id);

    Optional<User> update(User user);

    void delete(Long id);

}
