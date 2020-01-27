package mate.academy.internetshop.controller.item.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class DeleteItemsController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    private static Logger logger = Logger.getLogger(DeleteItemsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String itemId = req.getParameter("item_id");
        try {
            itemService.delete(Long.valueOf(itemId));
        } catch (DataProcessingException e) {
            logger.error(e);
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
