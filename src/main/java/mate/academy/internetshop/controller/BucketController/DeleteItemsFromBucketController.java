package mate.academy.internetshop.controller.BucketController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

public class DeleteItemsFromBucketController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession(true).getAttribute("user_id");
        String itemId = req.getParameter("item_id");

        User user = userService.get(userId);
        Bucket bucket = bucketService.getByUserId(user.getUserId());
        Item item = itemService.get(Long.valueOf(itemId));
        bucketService.deleteItem(bucket, item);

        resp.sendRedirect(req.getContextPath() + "/servlet/getBucket");
    }
}
