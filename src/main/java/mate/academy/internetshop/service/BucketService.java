package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService {
    Bucket add(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    Optional<Bucket> update(Bucket bucket);

    void delete(Long id);

    void addItem(Bucket bucket, Item item);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket); //remove all items from bucket

    Optional<List<Item>> getAllItems(Bucket bucket);

}
