package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setBucketId(IdGenerator.getBucketId());
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
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

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}
