package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

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

//import org.apache.commons.io.

@WebServlet(urlPatterns = "/category/list")

//@WebServlet(urlPatterns = "/images")
public class CategoryListController extends HttpServlet {
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
        String userUsername = user.getUsername();  // Sửa: dùng username string thay getId()

        List<CategoryModel> categories = service.findAllByUserId(userUsername);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);  // Đảm bảo path /views/list.jsp tồn tại
    }
}