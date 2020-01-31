package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getCookies() == null) {
            LOGGER.info("Cookies are empty");
            processAnAuthenticated(req, resp);
            return;
        }
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                try {
                    Optional<User> user = userService.getByToken(cookie.getValue());
                    if (user.isPresent()) {
                        LOGGER.info("User " + user.get().getUserName() + " was authenticated");
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                } catch (DataProcessingException e) {
                    LOGGER.error(e);
                    req.setAttribute("errorMsg", e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp").forward(req, resp);
                }
            }
        }
        processAnAuthenticated(req, resp);
    }

    @Override
    public void destroy() {

    }

    private void processAnAuthenticated(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
