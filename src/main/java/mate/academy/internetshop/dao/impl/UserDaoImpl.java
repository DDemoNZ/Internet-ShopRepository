package mate.academy.internetshop.dao.impl;

import java.util.List;
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
    public User create(User user) {
        user.setUserId(IdGenerator.getUserId());
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long userId) {
        return Storage.users
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public User update(User user) {
        User oldUser = get(user.getUserId()).orElseThrow(()
                -> new NoSuchElementException("Can't update user with id " + user.getUserId()));
        int index = Storage.users.indexOf(oldUser);
        return Storage.users.set(index, user);
    }

    @Override
    public boolean delete(Long userId) {
        Optional<User> deletedUserOptional = get(userId);
        if (deletedUserOptional.isPresent()) {
            User deletedUser = deletedUserOptional.get();
            Storage.users.removeIf(user -> user.getUserId().equals(deletedUser.getUserId()));
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
