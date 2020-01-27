package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private Long userId;
    private Long orderId;
    private List<Item> items;
    private Double allPrice;

    public Order(Double allPrice, User user) {
        this.items = new ArrayList<>();
        this.allPrice = allPrice;
        this.userId = user.getUserId();
    }

    public Order(Long userId, Long orderId, List<Item> items, Double allPrice) {
        this.userId = userId;
        this.orderId = orderId;
        this.items = items;
        this.allPrice = allPrice;
    }

    public Double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Double allPrice) {
        this.allPrice = allPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(userId, order.userId)
                && Objects.equals(orderId, order.orderId)
                && Objects.equals(items, order.items)
                && Objects.equals(allPrice, order.allPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderId, items, allPrice);
    }

    @Override
    public String toString() {
        return "Order{"
                + "userId=" + userId
                + ", orderId=" + orderId
                + ", items=" + items
                + ", allPrice=" + allPrice
                + '}';
    }
}
