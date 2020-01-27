package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Inject
    private static BucketService bucketService;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow(()
                -> new NoSuchElementException("Can't find order with id " + id));
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Double allPrice = items.stream()
                .map(Item::getPrice)
                .mapToDouble(elem -> elem).sum();
        List<Item> orderItems = new ArrayList<>(items);
        Order order = new Order(allPrice, user);
        order.setItems(orderItems);
        Bucket bucket = bucketService.getByUserId(user.getUserId());
        bucketService.clear(bucket);
        return create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAllOrdersForUser(user);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
