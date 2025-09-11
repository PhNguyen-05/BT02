package vn.iotstar.controllers;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import vn.iotstar.models.CategoryModel;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.utils.Constant;  // Thêm import này

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,    // 1MB
    maxFileSize = 1024 * 1024 * 5,      // 5MB
    maxRequestSize = 1024 * 1024 * 5 * 10  // 50MB
)
@WebServlet(urlPatterns = "/category/add")
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
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
        String userUsername = user.getUsername();

        String cateName = req.getParameter("cateName");  // Vẫn dùng getParameter cho text fields
        if (cateName == null || cateName.trim().isEmpty()) {
            req.setAttribute("alert", "Tên category không được rỗng");
            req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
            return;
        }

        // Xử lý upload file TRƯỚC
        String icons = "";  // Default empty nếu không upload
        Part filePart = req.getPart("icons");  // name="icons" từ form
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                // Basic validation: chỉ cho phép image files
                if (!isValidImage(fileName)) {
                    req.setAttribute("alert", "Chỉ chấp nhận file hình ảnh (jpg, png, gif)");
                    req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
                    return;
                }

                // Tạo thư mục nếu chưa có
                File uploadDir = new File(Constant.DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String uploadPath = Constant.DIR + File.separator + fileName;
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, Paths.get(uploadPath), StandardCopyOption.REPLACE_EXISTING);
                    icons = fileName;  // Set filename cho DB
                } catch (IOException e) {
                    req.setAttribute("alert", "Lỗi upload file: " + e.getMessage());
                    req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
                    return;
                }
            }
        }

        // Tạo và insert category SAU upload
        CategoryModel category = new CategoryModel();
        category.setCateName(cateName);
        category.setIcons(icons);  // Sử dụng filename từ upload
        category.setUserUsername(userUsername);

        service.insert(category);
        resp.sendRedirect(req.getContextPath() + "/category/list");
    }

    // Method helper riêng (di chuyển ra ngoài doPost)
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            for (String token : contentDisp.split(";")) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }

    // Helper validation image type
    private boolean isValidImage(String fileName) {
        String lowerFileName = fileName.toLowerCase();
        return lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") ||
               lowerFileName.endsWith(".png") || lowerFileName.endsWith(".gif");
    }
}