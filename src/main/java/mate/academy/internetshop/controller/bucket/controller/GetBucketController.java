package mate.academy.internetshop.controller.bucket.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import org.apache.log4j.Logger;

public class GetBucketController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GetBucketController.class);
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession(true).getAttribute("user_id");
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            req.setAttribute("bucket", bucket);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbErrors.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
