package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static String DB_NAME = "internetshop";
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String name = item.getName();
        Double price = item.getPrice();
        String query = String.format(Locale.ROOT,
                "INSERT INTO %s.items (name, price) VALUES ('%s', '%.4f');",
                DB_NAME, name, price);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can't create item with id " + item.getItemId(), e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        String query = String.format(
                "SELECT * FROM %s.items WHERE item_id = '%d';", DB_NAME, itemId);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Long tempItemId = rs.getLong("item_id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(tempItemId);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            logger.warn("Can't get item with id " + itemId, e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        String name = item.getName();
        Double price = item.getPrice();
        Long itemId = item.getItemId();
        String query = String.format(Locale.ROOT,
                "UPDATE %s.items SET name = '%s', price = '%.4f' WHERE item_id = '%d'",
                DB_NAME, name, price, itemId);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't update item with id " + itemId, e);
        }
        return item;
    }

    @Override
    public boolean delete(Long itemId) {
        Optional<Item> item = get(itemId);
        if (item.isPresent()) {
            String query = String.format(
                    "DELETE FROM %s.items WHERE item_id = '%d';", DB_NAME, itemId);
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                logger.warn("Can't delete item with id " + itemId, e);
            }
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        String query = String.format("SELECT * FROM %s.items;", DB_NAME);
        List<Item> itemList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Long newItemId = rs.getLong("item_id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(newItemId);
                itemList.add(item);
            }
        } catch (SQLException e) {
            logger.warn("Can't get all items from DB", e);
        }
        return itemList;
    }
}
