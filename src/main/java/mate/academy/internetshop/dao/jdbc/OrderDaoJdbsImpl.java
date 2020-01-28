package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

@Dao
public class OrderDaoJdbsImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoJdbsImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {

        String query = "INSERT INTO internetshop.orders (user_id, amount_price) VALUES (?, ?);";
        Long orderId = null;

        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.setDouble(2, order.getAllPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                orderId = generatedKeys.getLong(1);
            }
            order.setOrderId(orderId);
            addOrderItems(order, order.getItems());
        } catch (SQLException | DataProcessingException e) {
            throw new DataProcessingException("Can't create order with id " + orderId + "\n" + e);
        }

        return order;
    }

    private void addOrderItems(Order newOrder, List<Item> items) throws DataProcessingException {
        String insertOrderItemQuery =
                "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?);";

        for (Item item : items) {
            try (PreparedStatement statement = connection.prepareStatement(insertOrderItemQuery)) {
                statement.setLong(1, newOrder.getOrderId());
                statement.setLong(2, item.getItemId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Can't insert order's items into DB "
                        + newOrder.getOrderId() + "\n" + e);
            }
        }
    }

    @Override
    public Optional<Order> get(Long orderId) throws DataProcessingException {

        String query = "SELECT * FROM orders WHERE order_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                Double amountPrice = resultSet.getDouble("amount_price");
                Long orderId1 = resultSet.getLong("order_id");
                List<Item> orderItems = getItemsFromOrder(orderId1);
                Order order = new Order(userId, orderId1, orderItems, amountPrice);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get from DB order with id "
                    + orderId + "\n" + e);
        }

        return Optional.empty();
    }

    private List<Item> getItemsFromOrder(long orderId1) throws DataProcessingException {
        String query = "SELECT items.item_id, name, price FROM items JOIN orders_items ON"
                + " items.item_id = orders_items.item_id AND order_id = ?;";
        List<Item> itemList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId1);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("items.item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(itemId);
                itemList.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order's items from order with id "
                    + orderId1 + "\n" + e);
        }

        return itemList;
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        String query = "UPDATE orders SET user_id = ?, amount_price = ? WHERE order_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getUserId());
            statement.setDouble(2, order.getAllPrice());
            statement.setLong(3, order.getOrderId());
            statement.executeUpdate();
            List<Item> oldO = getItemsFromOrder(order.getOrderId());
            List<Item> newO = order.getItems();
            List<Item> toDelete = new ArrayList<>(oldO);
            toDelete.removeAll(newO);
            deleteItemsFromOrder(order, toDelete);
            List<Item> toAdd = new ArrayList<>(newO);
            toAdd.removeAll(oldO);
            addOrderItems(order, toAdd);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order with id "
                    + order.getOrderId() + "\n" + e);
        }

        return order;
    }

    private void deleteItemsFromOrder(Order order, List<Item> toDelete)
            throws DataProcessingException {
        String query = "DELETE FROM orders_items WHERE order_id = ? AND item_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Item item : toDelete) {
                statement.setLong(1, order.getOrderId());
                statement.setLong(2, item.getItemId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete items from order with id "
                    + order.getOrderId() + "\n" + e);
        }
    }

    @Override
    public boolean delete(Long orderId) throws DataProcessingException {
        Order order = get(orderId).get();
        List<Item> itemsFromOrder = order.getItems();
        deleteItemsFromOrder(order, itemsFromOrder);

        String query = "DELETE FROM orders WHERE order_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order with id "
                    + orderId + "\n" + e);
        }
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                Long orderId = resultSet.getLong("order_id");
                Double amountPrice = resultSet.getDouble("amount_price");
                List<Item> itemsFromOrder = getItemsFromOrder(orderId);
                Order order = new Order(userId, orderId, itemsFromOrder, amountPrice);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all orders from DB " + "\n" + e);
        }

        return orders;
    }

    public Double getAmountPrice(Long orderId) throws DataProcessingException {
        String query = "SELECT SUM(price) AS amount_price FROM users JOIN orders"
                + " ON users.user_id = orders.user_id JOIN orders_items"
                + " ON orders.order_id = orders_items.order_id JOIN items"
                + " ON orders_items.item_id = items.item_id WHERE orders.order_id = ?;";
        Double amountPrice = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amountPrice = resultSet.getDouble("amount_price");
                return amountPrice;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't count amount price from order with id "
                    + orderId + "\n" + e);
        }

        return amountPrice;
    }

    @Override
    public List<Order> getAllOrdersForUser(User user) throws DataProcessingException {

        List<Order> orderList = new ArrayList<>();
        String getUsersOrdersQuery = "SELECT * FROM orders WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(getUsersOrdersQuery)) {
            statement.setLong(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long orderId = resultSet.getLong("order_id");
                resultSet.getLong("user_id");
                List<Item> itemsFromOrder = getItemsFromOrder(orderId);
                Double amountPrice = getAmountPrice(orderId);
                Order order = new Order(user.getUserId(), orderId, itemsFromOrder, amountPrice);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user's orders with user id"
                    + user.getUserId() + "\n" + e);
        }

        return orderList;
    }
}
