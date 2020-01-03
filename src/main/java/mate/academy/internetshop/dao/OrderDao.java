package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.model.Order;

public interface OrderDao {

    Order add(Order order);

    Optional<Order> get(Long id);

    Optional<Order> update(Order order);

    boolean delete(Long id);
}
