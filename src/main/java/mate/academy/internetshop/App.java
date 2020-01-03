package mate.academy.internetshop;

import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class App {
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
        System.out.println("e");
//        java.lang.ExceptionInInitializerError
//        Caused by: java.lang.IllegalArgumentException:
//        Can not set static mate.academy.internetshop.dao.BucketDao
//         field mate.academy.internetshop.service.impl.BucketServiceImpl.bucketDao
//         to mate.academy.internetshop.dao.impl.UserDaoImpl
//        А вылетает в инжекторе почему-то
//        Injector.java:39)
    }
}
