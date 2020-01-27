package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    @Inject
    private static ItemDao itemDao;

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets (user_id) VALUES (?);";

        try (PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            Long bucketId = null;
            while (generatedKeys.next()) {
                bucketId = generatedKeys.getLong(1);
            }
            bucket.setBucketId(bucketId);
        } catch (SQLException e) {
            logger.warn("Can't create bucket", e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        String query = "SELECT * FROM buckets WHERE bucket_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                Bucket bucket = new Bucket(userId);
                bucket.setBucketId(resultSet.getLong("bucket_id"));
                List<Item> items = getItemsFromBucket(bucketId);
                bucket.setItems(items);
                return Optional.of(bucket);
            }
        } catch (SQLException e) {
            logger.warn("Can't get user id with bucket id " + bucketId, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Item> getItemsFromBucket(Long bucketId) {
        String query = "SELECT item_id FROM buckets INNER JOIN bucket_items ON buckets.bucket_id "
                + "= bucket_items.bucket_id WHERE bucket_items.bucket_id = ?;";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                items.add(itemDao.get(itemId).get());
            }
        } catch (SQLException e) {
            logger.warn("Can't get items from bucket with bucket id" + bucketId, e);
        }
        return items;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String deleteItems = "DELETE FROM bucket_items WHERE bucket_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(deleteItems)) {
            statement.setLong(1, bucket.getBucketId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't update (D) bucket with id " + bucket.getBucketId(), e);
        }

        String addItems = "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(addItems)) {
            for (Item item : bucket.getItems()) {
                statement.setLong(1, bucket.getBucketId());
                statement.setLong(2, item.getItemId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.warn("Can't update (I) bucket with id " + bucket.getBucketId(), e);
        }

        return bucket;
    }

    private List<Item> getBucketsItemsId(Long bucketId) {
        String query = "SELECT items.item_id, name, price FROM items JOIN bucket_items "
                + "ON items.item_id = buckets_items.item_id WHERE bucket_id = ?;";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("items.item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setItemId(itemId);
                itemList.add(item);
            }
        } catch (SQLException e) {
            logger.warn("Can't get buckets items id from bucket with id " + bucketId, e);
        }
        return itemList;
    }

    @Override
    public boolean delete(Long bucketId) {
        String query = "DELETE FROM buckets WHERE bucket_id = ?;";
        List<Item> allItemsFromBucket = getAllItemsFromBucket(bucketId);
        for (Item item : allItemsFromBucket) {
            deleteItemsFromBucket(bucketId, item.getItemId());
        }
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn("Can't delete bucket with id " + bucketId, e);
        }
        return false;
    }

    private List<Item> getAllItemsFromBucket(Long bucketId) {
        String query = "SELECT item_id FROM buckets INNER JOIN bucket_items"
                + " ON buckets.bucket_id = buckets_items.bucket_id WHERE buckets.bucket_id = ?;";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                itemList.add(itemDao.get(itemId).get());
            }
        } catch (SQLException e) {
            logger.warn("Can't get items from bucket with id " + bucketId, e);
        }
        return itemList;
    }

    @Override
    public void deleteItemsFromBucket(Long bucketId, Long itemId) {
        String query = "DELETE FROM bucket_items WHERE bucket_id = ? AND item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't delete items with id " + itemId
                    + " from bucket with id " + bucketId, e);
        }
    }

    @Override
    public List<Bucket> getAll() {
        String query = "SELECT bucket_id FROM buckets;";
        List<Bucket> buckets = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong("bucket_id");
                Bucket bucket = get(bucketId).get();
                buckets.add(bucket);
            }
            return buckets;
        } catch (SQLException e) {
            logger.warn("Can't get all buckets", e);
        }
        return null;
    }

    @Override
    public void addItemToBucket(Long bucketId, Long itemId) {
        String query = "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't add item to bucket with id " + bucketId, e);
        }
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = get(bucketId).get();
        List<Item> items = bucket.getItems();
        for (Item item : items) {
            deleteItemsFromBucket(bucketId, item.getItemId());
        }
        return bucket;
    }
}
