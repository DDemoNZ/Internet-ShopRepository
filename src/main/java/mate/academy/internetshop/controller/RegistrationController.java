package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class RegistrationController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User newUser = new User(req.getParameter("username"));
        newUser.setPassword(req.getParameter("password"));
        newUser.setFirstName(req.getParameter("first_name"));
        newUser.setSecondName(req.getParameter("second_name"));

        User user = userService.create(newUser);
        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);
        HttpSession session = req.getSession(true);
        session.setAttribute("user_id", user.getUserId());
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
