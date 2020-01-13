package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
        return Storage.items
                .stream()
                .filter(item -> item.getItemId().equals(id))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        IntStream.range(0, Storage.items.size())
                .filter(it -> item.getItemId().equals(Storage.items.get(it).getItemId()))
                .forEach(i -> Storage.items.set(i, item));
        return item;
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

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}
