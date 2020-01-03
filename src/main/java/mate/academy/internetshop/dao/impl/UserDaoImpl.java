package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        user.setUserId(IdGenerator.getUserId());
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long UserId) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(user -> user.getUserId().equals(UserId))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find user with id "
                        + UserId)));
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> updatedUserOptional = get(user.getUserId());
        if (updatedUserOptional.isPresent()) {
            User updatedUser = updatedUserOptional.get();
            updatedUser.setUserId(user.getUserId());
            updatedUser.setName(user.getName());
            updatedUser.setBucket(user.getBucket());
            return Optional.of(updatedUser);
        }
        return Optional.of(user);
    }

    @Override
    public boolean delete(Long UserId) {
        Optional<User> deletedUserOptional = get(UserId);
        if (deletedUserOptional.isPresent()) {
            User deletedUser = deletedUserOptional.get();
            Storage.users.removeIf(user -> user.getUserId().equals(deletedUser.getUserId()));
            return true;
        }
        return false;
    }
}
