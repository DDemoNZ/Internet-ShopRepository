package mate.academy.internetshop.model;

import java.util.List;

import mate.academy.internetshop.lib.IdGenerator;

public class Bucket {

    private List<Item> items;
    private Long userId;
    private Long bucketId;

    public Bucket(List<Item> items, Long userId) {
        this.items = items;
        this.userId = userId;
        bucketId = IdGenerator.getBucketId();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }
}
