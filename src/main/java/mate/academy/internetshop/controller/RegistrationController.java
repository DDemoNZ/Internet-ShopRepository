package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.internetshop.dao.jdbc.ItemDaoJdbcImpl;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {

    @Inject
    private static UserService userService;

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        User newUser = new User(username);

        newUser.setPassword(req.getParameter("password"));
        newUser.setFirstName(req.getParameter("first_name"));
        newUser.setSecondName(req.getParameter("second_name"));
        newUser.addRole(Role.of("USER"));

        try {
            if (userService.checkLogin(username)) {
                throw new AuthenticationException("Login is already exists");
            }
            if (!req.getParameter("password").equals(req.getParameter("password-repeat"))) {
                throw new AuthenticationException("Passwords don't match");
            }
        } catch (AuthenticationException e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            return;
        }

        try {
            User user = userService.create(newUser);
            HttpSession session = req.getSession(true);
            session.setAttribute("user_id", user.getUserId());
        } catch (Exception e) {
            logger.warn("Can't reg user", e);
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
