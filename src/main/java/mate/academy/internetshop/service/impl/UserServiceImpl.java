package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        user.setToken(getToket());
        return userDao.create(user);
    }

    private String getToket() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId).orElseThrow(()
                -> new NoSuchElementException("Can't find user with id " + userId));
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long userId) {
        return userDao.delete(userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(String username, String password)
            throws mate.academy.internetshop.exceptions.AuthenticationException {
        return userDao.login(username, password);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public boolean checkLogin(String login) {
        return userDao.checkLogin(login);
    }
}
