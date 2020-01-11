package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        order.setOrderId(IdGenerator.getOrderId());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        Order oldOrder = get(order.getOrderId()).orElseThrow(()
                -> new NoSuchElementException("Can't update order with id " + order.getOrderId()));
        int index = Storage.items.indexOf(oldOrder);
        return Storage.orders.set(index, order);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Order> deletedOrderOptional = get(id);
        if (deletedOrderOptional.isPresent()) {
            Order deletedOrder = deletedOrderOptional.get();
            Storage.orders.removeIf(order -> order.getOrderId().equals(deletedOrder.getOrderId()));
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }
}
