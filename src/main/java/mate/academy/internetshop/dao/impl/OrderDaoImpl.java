package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Optional.ofNullable(Storage.orders
                .stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find order with id "
                        + id)));
    }

    @Override
    public Optional<Order> update(Order order) {
        Optional<Order> updatedOrderOptional = Optional.ofNullable(Storage.orders
                .stream()
                .filter(orderStream -> orderStream.getOrderId().equals(order.getOrderId()))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find order with id "
                        + order.getOrderId())));
        Order updatedOrder = updatedOrderOptional.get();
        updatedOrder.setItems(order.getItems());
        updatedOrder.setOrderId(order.getOrderId());
        updatedOrder.setUserId(order.getUserId());
        updatedOrder.setAllPrice(order.getAllPrice());
        return Optional.of(updatedOrder);
    }

    @Override
    public void delete(Long id) {
        Order deletedOrder = Storage.orders
                .stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find order with id " + id));
        Storage.orders.removeIf(order -> order.getOrderId().equals(deletedOrder.getOrderId()));
    }
}
