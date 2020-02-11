package mate.academy.internetshop.controller.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.OrderService;
import org.apache.log4j.Logger;

public class DeleteUserOrdersController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteUserOrdersController.class);
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderId = req.getParameter("order_id");
        try {
            orderService.delete(Long.valueOf(orderId));
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
