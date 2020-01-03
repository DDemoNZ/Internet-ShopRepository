package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find user with id "
                        + id)));
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> updatedUserOptional = Optional.ofNullable(Storage.users.stream()
                .filter(userOld -> userOld.getUserId().equals(user.getUserId()))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find user with id "
                        + user.getUserId())));
        User updatedUser = updatedUserOptional.get();
        updatedUser.setUserId(user.getUserId());
        updatedUser.setOrders(user.getOrders());
        updatedUser.setName(user.getName());
        updatedUser.setBucket(user.getBucket());
        return Optional.of(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User deletedUser = Storage.users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id " + id));
        Storage.users.removeIf(user -> user.getUserId().equals(deletedUser.getUserId()));
    }
}
