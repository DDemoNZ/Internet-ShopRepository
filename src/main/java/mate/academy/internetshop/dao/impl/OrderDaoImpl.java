package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.IdGenerator;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

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
        IntStream.range(0, Storage.orders.size())
                .filter(or -> order.getOrderId().equals(Storage.orders.get(or).getOrderId()))
                .forEach(i -> Storage.orders.set(i, order));
        return order;
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

    @Override
    public List<Order> getAllOrdersForUser(User user) {
        return getAll().stream()
                .filter(o -> o.getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }
}
