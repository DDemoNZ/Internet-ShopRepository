package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao extends GenericDao<Bucket, Long> {

    void clear(Long bucketId) throws DataProcessingException;

    void addItemToBucket(Long bucketId, Long itemId) throws DataProcessingException;

    void deleteItemsFromBucket(Long bucketId) throws DataProcessingException;

    List<Item> getItemsFromBucket(Long bucketId) throws DataProcessingException;
}
