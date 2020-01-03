package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        item.setItemId(IdGenerator.getItemId());
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(item -> item.getItemId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find item with id " + id)));
    }

    @Override
    public Optional<Item> update(Item item) {
        Optional<Item> updatedItemOptional = get(item.getItemId());
        if (updatedItemOptional.isPresent()) {
            Item updatedItem = updatedItemOptional.get();
            updatedItem.setPrice(item.getPrice());
            updatedItem.setItemId(item.getItemId());
            updatedItem.setName(item.getName());
            return Optional.of(updatedItem);
        }
        return Optional.of(item);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Item> deletedItemOptional = get(id);
        if (deletedItemOptional.isPresent()) {
            Item deletedItem = deletedItemOptional.get();
            Storage.items.removeIf(item -> item.equals(deletedItem));
            return true;
        }
        return false;
    }
}
