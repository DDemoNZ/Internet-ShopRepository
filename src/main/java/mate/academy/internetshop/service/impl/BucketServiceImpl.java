package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) throws DataProcessingException {
        return bucketDao.get(bucketId).orElseThrow(()
                -> new NoSuchElementException("Can't find bucket with id " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long id) throws DataProcessingException {
        return bucketDao.delete(id);
    }

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().add(item);
        update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().remove(item);
        update(bucket);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        bucket.getItems().clear();
        update(bucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) throws DataProcessingException {
        return bucketDao.get(bucket.getBucketId()).get().getItems();
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        return bucketDao.getAll();
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingException {
        Optional<Bucket> bucket = bucketDao.getByUserId(userId);
        if (bucket.isPresent()) {
            return bucket.get();
        }
        return create(new Bucket(userId));
    }
}
