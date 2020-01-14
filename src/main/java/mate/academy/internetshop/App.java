package mate.academy.internetshop;

import java.util.List;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class App {

    static final Logger logger = Logger.getLogger(App.class);

    @Inject
    private static ItemService itemService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Item item1 = new Item("item1", 1.0);
        itemService.create(item1);
        User user1 = new User("user1");
        userService.create(user1);
        Bucket bucket = new Bucket(user1.getUserId());
        bucketService.create(bucket);
        bucketService.addItem(bucket, item1);
        List<Item> allItems = bucketService.getAllItems(bucket);
        Order order = orderService.completeOrder(allItems, user1);
        System.out.println(order);
        System.out.println(user1);
        logger.info("INFO");
        logger.debug("DEBUG");
    }
}
