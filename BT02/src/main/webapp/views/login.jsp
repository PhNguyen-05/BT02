<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <style>
        .login-container { max-width: 400px; margin: 50px auto; }
        .login-input { width: 100%; padding: 10px; margin: 10px 0; }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập</h2>
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div>
                <label for="username">Tài khoản:</label>
                <input type="text" id="username" name="username" class="login-input" required>
            </div>
            <div>
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" class="login-input" required>
            </div>
            <div>
                <input type="checkbox" id="remember" name="remember" value="on">
                <label for="remember">Nhớ mật khẩu</label>
            </div>
            <button type="submit">Đăng nhập</button>
        </form>
    </div>
</body>
</html>