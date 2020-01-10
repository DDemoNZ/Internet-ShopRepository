package mate.academy.internetshop.dao.impl;

import java.util.List;
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
        Optional<User> updatedUserOptional = get(user.getUserId());
        if (updatedUserOptional.isPresent()) {
            User updatedUser = updatedUserOptional.get();
            updatedUser.setUserId(user.getUserId());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setSecondName(user.getSecondName());
            updatedUser.setBucket(user.getBucket());
            updatedUser.setUserName(user.getUserName());
            updatedUser.setPassword(user.getPassword());
            return updatedUser;
        }
        return user;
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
