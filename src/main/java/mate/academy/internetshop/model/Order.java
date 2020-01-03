package mate.academy.internetshop.model;

import java.util.List;

import mate.academy.internetshop.lib.IdGenerator;

public class Order {

    private Long userId;
    private Long orderId;
    private List<Item> items;
    private Double allPrice;

    public Order(List<Item> items, Double allPrice, User user) {
        this.items = items;
        this.allPrice = allPrice;
        this.userId = user.getUserId();
        orderId = IdGenerator.getOrderId();
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
}
