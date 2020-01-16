package mate.academy.internetshop.web.filters;

import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class AuthorizationFilter implements Filter {

    @Inject
    private static UserService userService;

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/getAllUsers", ADMIN);
        protectedUrls.put("/servlet/getAllItems", ADMIN);
        protectedUrls.put("/servlet/deleteItem", ADMIN);
        protectedUrls.put("/servlet/getAllOrders", ADMIN);

        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/getBucket", USER);
        protectedUrls.put("/servlet/deleteUserOrder", USER);
        protectedUrls.put("/servlet/orders", USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestedUrl = httpServletRequest.getServletPath();
        Role.RoleName roleNameAdmin = protectedUrls.get(requestedUrl);
        Role.RoleName roleNameUser = protectedUrls.get(requestedUrl);
        if (roleNameAdmin == null && roleNameUser == null) {
            processDenied(httpServletRequest, httpServletResponse);
            return;
        }

        Long userId = (Long) httpServletRequest.getSession().getAttribute("user_id");
        User user = userService.get(userId);
        if (verifyRole(user, roleNameAdmin) || verifyRole(user, roleNameUser)) {
            processAuthenticated(filterChain, httpServletRequest, httpServletResponse);
        } else {
            processDenied(httpServletRequest, httpServletResponse);
        }
    }

    private void processDenied(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }

    private void processUnAuthenticated(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
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
