package mate.academy.internetshop.model;

import java.util.List;

import mate.academy.internetshop.lib.IdGenerator;

public class User {

    private String name;
    private Long userId;
    private List<Order> orders;
    private Bucket bucket;

    public User(String name, List<Order> orders, Bucket bucket) {
        this.name = name;
        userId = IdGenerator.getUserId();
        this.orders = orders;
        this.bucket = bucket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
}
