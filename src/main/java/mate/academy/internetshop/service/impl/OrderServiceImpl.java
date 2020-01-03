package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Dao
    private OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Optional<Order> update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Double allPrice = items.stream()
                .map(Item::getPrice)
                .mapToDouble(elem -> elem).sum();
        Order order = new Order(items, allPrice, user);
        create(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return user.getOrders();
    }

}
