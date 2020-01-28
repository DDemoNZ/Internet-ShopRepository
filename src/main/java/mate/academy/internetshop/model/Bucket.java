package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bucket {

    private List<Item> items;
    private Long userId;
    private Long bucketId;

    public Bucket(Long userId) {
        items = new ArrayList<>();
        this.userId = userId;
    }

    public Bucket(List<Item> items, Long userId, Long bucketId) {
        this.items = items;
        this.userId = userId;
        this.bucketId = bucketId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bucket bucket = (Bucket) o;
        return Objects.equals(items, bucket.items)
                && Objects.equals(userId, bucket.userId)
                && Objects.equals(bucketId, bucket.bucketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, userId, bucketId);
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "items=" + items
                + ", userId=" + userId
                + ", bucketId=" + bucketId
                + '}';
    }
}
