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

//    private static BucketDao bucketDao;
//    private static ItemDao itemDao;
//    private static OrderDao orderDao;
//    private static UserDao userDao;
//    private static BucketService bucketService;
//    private static ItemService itemService;
//    private static OrderService orderService;
//    private static UserService userService;

    private static UserDao userDaoInstance;
    private static BucketDao bucketDaoInstance;
    private static ItemDao itemDaoInstance;
    private static OrderDao orderDaoInstance;

    private static OrderService orderServiceInstance;
    private static UserService userServiceInstance;
    private static ItemService itemServiceInstance;
    private static BucketService bucketServiceInstance;

    public static UserDao getUserDao() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoImpl();
        }
        return userDaoInstance;
    }

    public static BucketDao getBucketDao() {
        if (bucketDaoInstance == null) {
            bucketDaoInstance = new BucketDaoImpl();
        }
        return bucketDaoInstance;
    }

    public static ItemDao getItemDao() {
        if (itemDaoInstance == null) {
            itemDaoInstance = new ItemDaoImpl();
        }
        return itemDaoInstance;
    }

    public static OrderDao getOrderDao() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDaoImpl();
        }
        return orderDaoInstance;
    }

    public static OrderService getOrderService() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderServiceImpl();
        }
        return orderServiceInstance;
    }

    public static UserService getUserService() {
        if (userServiceInstance == null) {
            userServiceInstance = new UserServiceImpl();
        }
        return userServiceInstance;
    }

    public static ItemService getItemService() {
        if (itemServiceInstance == null) {
            itemServiceInstance = new ItemServiceImpl();
        }
        return itemServiceInstance;
    }

    public static BucketService getBucketService() {
        if (bucketServiceInstance == null) {
            bucketServiceInstance = new BucketServiceImpl();
        }
        return bucketServiceInstance;
    }
//    public static BucketDao getBucketDao() {
//        return bucketDao == null ? new BucketDaoImpl() : bucketDao;
//    }
//
//    public static ItemDao getItemDao() {
//        return itemDao == null ? new ItemDaoImpl() : itemDao;
//    }
//
//    public static OrderDao getOrderDao() {
//        return orderDao == null ? new OrderDaoImpl() : orderDao;
//    }
//
//    public static UserDao getUserDao() {
//        return userDao == null ? new UserDaoImpl() : userDao;
//    }
//
//    public static BucketService getBucketService() {
//        return bucketService == null ? new BucketServiceImpl() : bucketService;
//    }
//
//    public static ItemService getItemService() {
//        return itemService == null ? new ItemServiceImpl() : itemService;
//    }
//
//    public static OrderService getOrderService() {
//        return orderService == null ? new OrderServiceImpl() : orderService;
//    }
//
//    public static UserService getUserService() {
//        return userService == null ? new UserServiceImpl() : userService;
//    }

}
