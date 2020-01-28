package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> getByUsername(String username) throws DataProcessingException {
        String query = "SELECT * FROM users WHERE login = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user by username(login) " + username
                    + "\n" + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByToken(String token) throws DataProcessingException {
        String query = "SELECT * FROM users WHERE token = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user by token " + token + "\n" + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> login(String username) throws DataProcessingException {
        String query = "SELECT * FROM users WHERE login = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user with login " + username + "\n" + e);
        }
        return Optional.empty();
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {

        long userId = resultSet.getLong("user_id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        String firstName = resultSet.getString("firstName");
        String secondName = resultSet.getString("secondName");
        String token = resultSet.getString("token");

        User user = new User(login);
        user.setPassword(password);
        user.setSalt(salt);
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setToken(token);

        return user;
    }

    @Override
    public User create(User user) throws DataProcessingException {
        String query = "INSERT INTO users (firstName, secondName, login, password, salt, token) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        Long userId = null;
        Set<Role> roleToUser = new HashSet<>();
        roleToUser.add(Role.of("USER"));
        user.setRoles(roleToUser);

        try (PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setBytes(5, user.getSalt());
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(6, user.getToken());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
                user.setUserId(userId);
            }

            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                addRoleToUser(userId, role.getId());
            }
            bucketService.create(new Bucket(user.getUserId()));
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create user \n" + e);
        }

        return user;
    }

    private void addRoleToUser(Long userId, Long roleId) throws DataProcessingException {
        String addRole = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?);";
        roleId = 1L;

        try (PreparedStatement statement1 = connection.prepareStatement(addRole)) {
            statement1.setLong(1, userId);
            statement1.setLong(2, roleId); //DEFAULT ROLE USER
            statement1.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add role to user with id "
                    + userId + "\n" + e);
        }
    }

    @Override
    public Optional<User> get(Long userId) throws DataProcessingException {
        String query = "SELECT * FROM users WHERE user_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);

                Set<Role> roles = getUsersRoleByUserId(userId);
                user.setRoles(roles);

                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user with id " + userId + "\n" + e);
        }

        return Optional.empty();
    }

    public Set<Role> getUsersRoleByUserId(Long userId) throws DataProcessingException {
        Set<Role> roles = new HashSet<>();

        String getRoleByUserId = "SELECT roles.role_name FROM users "
                + "INNER JOIN user_roles ON users.user_id = user_roles.user_id "
                + "INNER JOIN roles ON user_roles.role_id = roles.role_id "
                + "WHERE users.user_id = ?;";

        try (PreparedStatement statement1 = connection.prepareStatement(getRoleByUserId)) {
            statement1.setLong(1, userId);
            ResultSet resultSet1 = statement1.executeQuery();
            while (resultSet1.next()) {
                String roleName = resultSet1.getString("role_name");
                roles.add(Role.of(roleName));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user's role from user with id "
                    + userId + "\n" + e);
        }

        return roles;
    }

    @Override
    public User update(User user) throws DataProcessingException {
        String query = "UPDATE users SET firstName = ?, secondName = ?, "
                + "login = ?, password = ?, salt = ?, token = ? "
                + "WHERE user_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setBytes(5, user.getSalt());
            statement.setString(6, user.getToken());
            statement.setLong(7, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update user with id "
                    + user.getUserId() + "\n" + e);
        }

        return user;
    }

    @Override
    public boolean delete(Long userId) throws DataProcessingException {
        deleteUsersRoles(userId);
        User user = get(userId).get();

        for (Order order : orderService.getUserOrders(user)) {
            orderService.delete(order.getOrderId());
        }

        bucketService.delete(bucketService.getByUserId(userId).getBucketId());
        String query = "DELETE FROM users WHERE user_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user with id " + userId + "\n" + e);
        }
    }

    private void deleteUsersRoles(Long userId) throws DataProcessingException {
        String query = "DELETE FROM user_roles WHERE user_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user's role by user with id "
                    + userId + "\n" + e);
        }
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        String query = "SELECT user_id FROM users;";
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                User user = get(userId).get();
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all users from DB \n" + e);
        }

        return users;
    }
}
