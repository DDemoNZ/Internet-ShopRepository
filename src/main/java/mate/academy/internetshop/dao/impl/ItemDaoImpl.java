package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find item with id " + id)));
    }

    @Override
    public Optional<Item> update(Item item) {
        Optional<Item> updatedItemOprional = Optional.ofNullable(Storage.items.stream()
                .filter(itemStream -> itemStream.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find item with id " + item.getId())));
        Item updatedItem = updatedItemOprional.get();
        updatedItem.setPrice(item.getPrice());
        updatedItem.setId(item.getId());
        updatedItem.setName(item.getName());
        return Optional.of(updatedItem);
    }

    @Override
    public void delete(Long id) {
        Item deletedItem = Storage.items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id " + id));
        Storage.items.removeIf(item -> item.equals(deletedItem));
    }
}
