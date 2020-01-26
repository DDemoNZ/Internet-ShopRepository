package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static final String DB_NAME = "internetshop";
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO items (name, price) VALUES (?, ?);";
        Item newItem = new Item(item.getName(), item.getPrice());
        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                long itemId = generatedKeys.getLong(1);
                newItem.setItemId(itemId);
            }
        } catch (SQLException e) {
            logger.warn("Can't create item", e);
        }
        return newItem;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        String query = "SELECT * FROM items WHERE item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(id);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            logger.warn("Can't get item with id" + itemId, e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE items SET name = ?, price = ? WHERE item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't update item with id " + item.getItemId(), e);
        }
        return item;
    }

    @Override
    public boolean delete(Long itemId) {
        String query = "DELETE FROM items WHERE item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn("Can't delete item with item id " + itemId, e);
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM items;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(itemId);
                itemList.add(item);
            }
        } catch (SQLException e) {
            logger.warn("Can't get all items from DB", e);
        }
        return itemList;
    }
}
