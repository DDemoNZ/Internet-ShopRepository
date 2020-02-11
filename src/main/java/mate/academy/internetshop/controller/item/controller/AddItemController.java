package mate.academy.internetshop.controller.item.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class AddItemController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddItemController.class);
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Item newItem = new Item(req.getParameter("name"),
                Double.valueOf(req.getParameter("price")));
        try {
            itemService.create(newItem);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
