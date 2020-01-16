package mate.academy.internetshop.lib;

import java.util.HashMap;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.FactoryForAllDao;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class AnnotatedClassMap {

    private static final Map<Class<?>, Object> classMap = new HashMap<>();

    static {
        classMap.put(BucketDao.class, FactoryForAllDao.getBucketDao());
        classMap.put(ItemDao.class, FactoryForAllDao.getItemDao());
        classMap.put(OrderDao.class, FactoryForAllDao.getOrderDao());
        classMap.put(UserDao.class, FactoryForAllDao.getUserDao());
        classMap.put(UserService.class, FactoryForAllDao.getUserService());
        classMap.put(BucketService.class, FactoryForAllDao.getBucketService());
        classMap.put(ItemService.class, FactoryForAllDao.getItemService());
        classMap.put(OrderService.class, FactoryForAllDao.getOrderService());
    }

    public static Object getImplementation(Class<?> interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
