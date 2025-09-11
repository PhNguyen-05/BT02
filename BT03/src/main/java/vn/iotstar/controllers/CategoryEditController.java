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

@WebServlet(urlPatterns = "/category/edit")
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cateId = Integer.parseInt(req.getParameter("cateId"));
        CategoryModel category = service.findById(cateId);
        if (category == null) {
            resp.sendRedirect(req.getContextPath() + "/category/list");
            return;
        }
        req.setAttribute("category", category);
        req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        UserModel user = (UserModel) session.getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int cateId = Integer.parseInt(req.getParameter("cateId"));
        String cateName = req.getParameter("cateName");
        String icons = req.getParameter("icons");

        if (cateName == null || cateName.trim().isEmpty()) {
            req.setAttribute("alert", "Tên category không được rỗng");
            req.setAttribute("category", service.findById(cateId));
            req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra quyền sở hữu
        CategoryModel existingCategory = service.findById(cateId);
        if (existingCategory == null || !existingCategory.getUserUsername().equals(user.getUsername())) {
            req.setAttribute("alert", "Bạn không có quyền chỉnh sửa danh mục này");
            req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);
            return;
        }

        CategoryModel category = new CategoryModel();
        category.setCateId(cateId);
        category.setCateName(cateName);
        category.setIcons(icons != null ? icons : "");
        category.setUserUsername(user.getUsername());  // Sửa: dùng username string

        service.update(category);
        resp.sendRedirect(req.getContextPath() + "/category/list");
    }
}