package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/category/delete")
public class CategoryDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserModel user = (UserModel) session.getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int cateId = Integer.parseInt(req.getParameter("cateId"));
        CategoryModel category = service.findById(cateId);
        if (category == null) {
            resp.sendRedirect(req.getContextPath() + "/category/list");
            return;
        }

        // Kiểm tra quyền sở hữu
        if (!category.getUserUsername().equals(user.getUsername())) {
            resp.sendRedirect(req.getContextPath() + "/category/list");
            return;
        }

        service.delete(cateId);
        resp.sendRedirect(req.getContextPath() + "/category/list");
    }
}