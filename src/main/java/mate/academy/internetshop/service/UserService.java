package mate.academy.internetshop.service;

import java.util.List;

import javax.naming.AuthenticationException;

import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user);

    User get(Long userId);

    User update(User user);

    boolean delete(Long userId);

    List<User> getAll();

    User login(String username, String password);

}
