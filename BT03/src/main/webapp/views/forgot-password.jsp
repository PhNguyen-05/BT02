<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; display: flex; align-items: center; justify-content: center; height: 100vh; }
        .forgot-form { max-width: 400px; padding: 20px; background: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .alert { margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="forgot-form">
            <h3 class="text-center mb-4">Quên mật khẩu</h3>
            <c:if test="${alert != null}">
                <div class="alert alert-info">${alert}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${param.email}" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Gửi yêu cầu</button>
            </form>
            <p class="text-center mt-3"><a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a></p>
        </div>
    </div>
</body>
</html>