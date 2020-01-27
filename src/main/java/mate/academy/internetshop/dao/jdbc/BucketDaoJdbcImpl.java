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
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    @Inject
    private static ItemDao itemDao;

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
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
            throw new DataProcessingException("Can't create bucket \n" + e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) throws DataProcessingException {
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
            throw new DataProcessingException("Can't get bucket with id " + bucketId + "\n" + e);
        }
        return Optional.empty();
    }

    @Override
    public List<Item> getItemsFromBucket(Long bucketId) throws DataProcessingException {
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
            throw new DataProcessingException("Can't get items from buckt with id " + bucketId
                    + "\n" + e);
        }
        return items;
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        String deleteItems = "DELETE FROM bucket_items WHERE bucket_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(deleteItems)) {
            statement.setLong(1, bucket.getBucketId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update(d) bucket with id "
                    + bucket.getBucketId() + "\n" + e);
        }

        String addItems = "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(addItems)) {
            for (Item item : bucket.getItems()) {
                statement.setLong(1, bucket.getBucketId());
                statement.setLong(2, item.getItemId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update(i) bucket with id "
                    + bucket.getBucketId() + "\n" + e);
        }
        return bucket;
    }

    @Override
    public boolean delete(Long bucketId) throws DataProcessingException {
        String query = "DELETE FROM buckets WHERE bucket_id = ?;";
        deleteItemsFromBucket(bucketId);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete bucket with id " + bucketId
                    + "\n" + e);
        }
    }

    @Override
    public void deleteItemsFromBucket(Long bucketId) throws DataProcessingException {
        String query = "DELETE FROM bucket_items WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete items from bucket with id "
                    + bucketId + "\n" + e);
        }
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
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
            throw new DataProcessingException("Can't get all buckets" + "\n" + e);
        }
    }

    @Override
    public void addItemToBucket(Long bucketId, Long itemId) throws DataProcessingException {
        String query = "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add item to bucket with id "
                    + bucketId + "\n" + e);
        }
    }

    @Override
    public void clear(Long bucketId) throws DataProcessingException {
        String query = "DELETE FROM bucket_items WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't clear bucket with id " + bucketId + "\n" + e);
        }
    }
}


