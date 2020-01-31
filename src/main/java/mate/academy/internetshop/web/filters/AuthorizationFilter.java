package mate.academy.internetshop.web.filters;

import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    @Inject
    private static UserService userService;
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/servlet/getAllUsers", ADMIN);
        protectedUrls.put("/servlet/getAllItems", ADMIN);
        protectedUrls.put("/servlet/deleteItem", ADMIN);
        protectedUrls.put("/servlet/getAllOrders", ADMIN);

        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/getBucket", USER);
        protectedUrls.put("/servlet/deleteUserOrder", USER);
        protectedUrls.put("/servlet/orders", USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", USER);
        protectedUrls.put("/servlet/internetShop", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestedUrl = httpServletRequest.getServletPath();
        Role.RoleName roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            processAuthenticated(filterChain, httpServletRequest, httpServletResponse);
            return;
        }

        Long userId = (Long) httpServletRequest.getSession().getAttribute("user_id");
        try {
            User user = userService.get(userId);
            if (verifyRole(user, roleName)) {
                processAuthenticated(filterChain, httpServletRequest, httpServletResponse);
                return;
            } else {
                processDenied(httpServletRequest, httpServletResponse);
            }
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            httpServletRequest.setAttribute("errorMsg", e.getMessage());
            httpServletRequest.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp")
                    .forward(httpServletRequest, httpServletResponse);
        } catch (NoSuchElementException e) {
            LOGGER.error(e);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/logout");
        }
    }

    private void processDenied(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream().anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processAuthenticated(FilterChain filterChain,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
