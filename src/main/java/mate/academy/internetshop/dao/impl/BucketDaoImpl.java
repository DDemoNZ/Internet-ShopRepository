package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

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
                .filter(b -> b.getBucketId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
        IntStream.range(0, Storage.buckets.size())
                .filter(b -> bucket.getBucketId().equals(Storage.buckets.get(b).getBucketId()))
                .forEach(i -> Storage.buckets.set(i, bucket));
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

    @Override
    public Bucket clear(Long bucketId) {
        return null;
    }

    @Override
    public void addItemToBucket(Long bucketId, Long itemId) {

    }

    @Override
    public void deleteItemsFromBucket(Long bucketId, Long itemId) {

    }

    @Override
    public List<Item> getItemsFromBucket(Long bucketId) {
        return null;
    }
}
