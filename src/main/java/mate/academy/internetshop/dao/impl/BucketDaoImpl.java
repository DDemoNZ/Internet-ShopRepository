package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket add(Bucket bucket) {
        bucket.setBucketId(IdGenerator.getBucketId());
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Optional.ofNullable(Storage.buckets
                .stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id "
                        + bucketId)));
    }

    @Override
    public Bucket update(Bucket bucket) { //Нужен ли тут Optional?
        Optional<Bucket> updatedBucketOptional = get(bucket.getBucketId());
        if (updatedBucketOptional.isPresent()) {
            Bucket updatedBucket = updatedBucketOptional.get();
            updatedBucket.setUserId(bucket.getUserId());
            updatedBucket.setItems(bucket.getItems());
            updatedBucket.setBucketId(bucket.getBucketId());
            return updatedBucket;
        }
        return bucket;
    }

    @Override
    public boolean delete(Long bucketId) {
        Optional<Bucket> deletedBucketOptional = get(bucketId);
        if (deletedBucketOptional.isPresent()) {
            Bucket deletedBucket = deletedBucketOptional.get();
            Storage.buckets.removeIf(bucket -> bucket.equals(deletedBucket));
            return true;
        }
        return false;
    }
}
