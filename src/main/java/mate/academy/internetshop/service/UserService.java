package mate.academy.internetshop.service;

import mate.academy.internetshop.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User get(Long userId);

    User update(User user);

    boolean delete(Long userId);

    List<User> getAll();
}
