package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException {
        user.setToken(getToket());
        return userDao.create(user);
    }

    private String getToket() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User get(Long userId) throws DataProcessingException {
        return userDao.get(userId).orElseThrow(()
                -> new NoSuchElementException("Can't find user with id " + userId));
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long userId) throws DataProcessingException {
        return userDao.delete(userId);
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        return userDao.getAll();
    }

    @Override
    public User login(String username, String password) throws AuthenticationException,
            DataProcessingException {
        Optional<User> user = userDao.login(username);
        if (user.isEmpty()
                || !HashUtil.hashPassword(password, user.get().getSalt()).equals(user.get().getPassword())) {
            throw new AuthenticationException("Invalid login or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) throws DataProcessingException {
        return userDao.getByToken(token);
    }

    @Override
    public boolean checkLogin(String login) throws DataProcessingException {
        return userDao.login(login).isPresent();
    }
}
