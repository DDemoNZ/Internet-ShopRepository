package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) {
        return bucketDao.get(bucketId).orElseThrow(()
                -> new NoSuchElementException("Can't find bucket with id " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long id) {
        return bucketDao.delete(id);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        Bucket tempBucket = bucketDao.get(bucket.getBucketId()).get();
        tempBucket.getItems().add(item);
        bucketDao.update(tempBucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        Bucket tempBucket = bucketDao.get(bucket.getBucketId()).get();
        List<Item> itemOfBucket = tempBucket.getItems();
        itemOfBucket.remove(item);
        bucketDao.update(tempBucket);
    }

    @Override
    public void clear(Bucket bucket) {
        Bucket tempBucket = bucketDao.get(bucket.getBucketId()).get();
        tempBucket.getItems().clear();
        bucketDao.update(tempBucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getBucketId()).get().getItems();
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }
}
