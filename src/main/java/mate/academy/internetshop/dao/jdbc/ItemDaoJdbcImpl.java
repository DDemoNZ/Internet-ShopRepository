package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
        String query = "INSERT INTO items (name, price) VALUES (?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                long itemId = generatedKeys.getLong(1);
                item.setItemId(itemId);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create item \n" + e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) throws DataProcessingException {
        String query = "SELECT * FROM items WHERE item_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(id);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get item with id " + itemId + "\n" + e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        String query = "UPDATE items SET name = ?, price = ? WHERE item_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get item with id " + item.getItemId()
                    + "\n" + e);
        }
        return item;
    }

    @Override
    public boolean delete(Long itemId) throws DataProcessingException {
        String query = "DELETE FROM items WHERE item_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete item with id " + itemId + "\n" + e);
        }
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
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
            throw new DataProcessingException("Can't get all items from DB \n" + e);
        }
        return itemList;
    }
}
