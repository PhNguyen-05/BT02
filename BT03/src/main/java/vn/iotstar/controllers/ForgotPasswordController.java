package vn.iotstar.controllers;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;  // Fix serialVersionUID warning
    
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USER = "your-email@gmail.com";  // Thay bằng email của bạn
    private static final String EMAIL_PASS = "your-app-password";  // App Password từ Google

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String alertMsg = "";

        if (email == null || email.trim().isEmpty()) {
            alertMsg = "Vui lòng nhập email!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        UserModel user = service.getByEmail(email);

        if (user != null) {
            // Tạo token ngẫu nhiên
            String resetToken = UUID.randomUUID().toString();
            // Lưu token vào session tạm (hoặc DB nếu cần)
            req.getSession().setAttribute("resetToken_" + email, resetToken);
            req.getSession().setAttribute("resetEmail", email);

            // Fix lỗi: Sử dụng req.getContextPath() thay vì getRequestContextPath()
            String resetUrl = req.getContextPath() + "/reset-password?token=" + resetToken;

            sendEmail(email, "Đặt lại mật khẩu", "Click vào link để đặt lại mật khẩu: " + resetUrl);

            alertMsg = "Email đặt lại mật khẩu đã được gửi! Vui lòng kiểm tra hộp thư.";
        } else {
            alertMsg = "Email không tồn tại trong hệ thống!";
        }
        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    private void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USER, EMAIL_PASS);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}