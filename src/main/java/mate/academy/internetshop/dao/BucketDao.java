package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao extends GenericDao<Bucket, Long> {

    Bucket clear(Long bucketId);

    void addItemToBucket(Long bucketId, Long itemId);

    void deleteItemsFromBucket(Long bucketId, Long itemId);

    List<Item> getItemsFromBucket(Long bucketId);
}
