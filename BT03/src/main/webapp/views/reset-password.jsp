<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; display: flex; align-items: center; justify-content: center; height: 100vh; }
        .reset-form { max-width: 400px; padding: 20px; background: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .alert { margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="reset-form">
            <h3 class="text-center mb-4">Đặt lại mật khẩu</h3>
            <c:if test="${alert != null}">
                <div class="alert alert-info">${alert}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/reset-password" method="post">
                <input type="hidden" name="email" value="${resetEmail}">
                <div class="mb-3">
                    <label for="newPassword" class="form-label">Mật khẩu mới</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Cập nhật mật khẩu</button>
            </form>
        </div>
    </div>
</body>
</html>