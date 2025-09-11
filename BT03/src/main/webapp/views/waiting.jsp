<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Waiting</title></head>
<body>
    <h2>Chào mừng, ${sessionScope.account.userName}!</h2>
    <p>Chờ xử lý...</p>
    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</body>
</html>