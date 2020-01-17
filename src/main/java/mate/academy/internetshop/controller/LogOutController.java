package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("user_id", "");

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                cookie.setMaxAge(0);
                cookie.setValue("");
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}