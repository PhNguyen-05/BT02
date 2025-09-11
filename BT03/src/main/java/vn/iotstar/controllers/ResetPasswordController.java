package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String email = (String) req.getSession().getAttribute("resetEmail");

        if (token != null && email != null) {
            String storedToken = (String) req.getSession().getAttribute("resetToken_" + email);
            if (token.equals(storedToken)) {
                req.setAttribute("resetEmail", email);
                RequestDispatcher rd = req.getRequestDispatcher("/views/reset-password.jsp");
                rd.forward(req, resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        String alertMsg = "";

        if (email == null || newPassword == null || confirmPassword == null ||
            newPassword.trim().isEmpty() || !newPassword.equals(confirmPassword)) {
            alertMsg = "Mật khẩu mới không hợp lệ hoặc không khớp!";
            req.setAttribute("alert", alertMsg);
            req.setAttribute("resetEmail", email);
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        UserModel user = service.getByEmail(email);

        if (user != null) {
            user.setPassword(newPassword);
            service.updatePassword(user);
            alertMsg = "Đặt lại mật khẩu thành công! Vui lòng đăng nhập.";
            req.setAttribute("alert", alertMsg);
            req.getSession().removeAttribute("resetToken_" + email);
            req.getSession().removeAttribute("resetEmail");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Lỗi hệ thống! Vui lòng thử lại.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }
}