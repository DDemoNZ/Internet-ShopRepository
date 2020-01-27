package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
        IntStream.range(0, Storage.users.size())
                .filter(u -> user.getUserId().equals(Storage.users.get(u).getUserId()))
                .forEach(i -> Storage.users.set(i, user));
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

    @Override
    public Optional<User> getByUsername(String username) {
        return Storage.users.stream()
                .filter(u -> u.getUserName().equals(username)).findFirst();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }

    @Override
    public Optional<User> login(String username) {
        return null;
    }

}
