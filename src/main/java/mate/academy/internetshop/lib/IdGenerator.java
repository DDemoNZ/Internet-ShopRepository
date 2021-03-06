package mate.academy.internetshop.lib;

public class IdGenerator {

    private static Long bucketId = 0L;
    private static Long itemId = 0L;
    private static Long orderId = 0L;
    private static Long userId = 0L;

    public static Long getBucketId() {
        return bucketId++;
    }

    public static Long getItemId() {
        return itemId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }

    public static Long getUserId() {
        return userId++;
    }
}
