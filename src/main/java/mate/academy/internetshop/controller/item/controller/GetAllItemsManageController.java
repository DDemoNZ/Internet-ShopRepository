package mate.academy.internetshop.controller.item.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class GetAllItemsManageController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    private static Logger logger = Logger.getLogger(GetAllItemsManageController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Item> items = null;
        try {
            items = itemService.getAll();
        } catch (DataProcessingException e) {
            logger.error(e);
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp").forward(req, resp);
        }

        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req, resp);
    }
}
