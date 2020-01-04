package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BucketDaoImpl;
import mate.academy.internetshop.dao.impl.ItemDaoImpl;
import mate.academy.internetshop.dao.impl.OrderDaoImpl;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

public class FactoryForAllDao {

    private static UserDao userDao;
    private static BucketDao bucketDao;
    private static ItemDao itemDao;
    private static OrderDao orderDao;
    private static OrderService orderService;
    private static UserService userService;
    private static ItemService itemService;
    private static BucketService bucketService;

    public static BucketDao getBucketDao() {
        return bucketDao == null ? new BucketDaoImpl() : bucketDao;
    }

    public static ItemDao getItemDao() {
        return itemDao == null ? new ItemDaoImpl() : itemDao;
    }

    public static OrderDao getOrderDao() {
        return orderDao == null ? new OrderDaoImpl() : orderDao;
    }

    public static UserDao getUserDao() {
        return userDao == null ? new UserDaoImpl() : userDao;
    }

    public static BucketService getBucketService() {
        return bucketService == null ? new BucketServiceImpl() : bucketService;
    }

    public static ItemService getItemService() {
        return itemService == null ? new ItemServiceImpl() : itemService;
    }

    public static OrderService getOrderService() {
        return orderService == null ? new OrderServiceImpl() : orderService;
    }

    public static UserService getUserService() {
        return userService == null ? new UserServiceImpl() : userService;
    }

}
