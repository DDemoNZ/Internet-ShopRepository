package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket add(Bucket bucket) {
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
    public Optional<Bucket> update(Bucket bucket) {
        Optional<Bucket> updatedBucketOptional = Optional.ofNullable(Storage.buckets.stream()
                .filter(bucketStream -> bucketStream.getBucketId().equals(bucket.getBucketId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id "
                        + bucket.getBucketId())));
        Bucket updatedBucket = updatedBucketOptional.get();
        updatedBucket.setUserId(bucket.getUserId());
        updatedBucket.setItems(bucket.getItems());
        updatedBucket.setBucketId(bucket.getBucketId());
        return Optional.of(updatedBucket);
    }

    @Override
    public void delete(Long bucketId) {
        Bucket deletedBucket = Storage.buckets.stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find bucket with id "
                        + bucketId));
        Storage.buckets.removeIf(bucket -> bucket.equals(deletedBucket));
    }
}
