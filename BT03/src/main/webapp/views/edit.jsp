<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sửa danh mục</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
}

.sidebar {
	background-color: #007bff;
	color: white;
	height: 100vh;
	padding: 20px;
}

.sidebar a {
	color: white;
	display: block;
	margin-bottom: 10px;
	text-decoration: none;
}

.sidebar a:hover {
	text-decoration: underline;
}

.header {
	background-color: #007bff;
	color: white;
	padding: 10px;
}

.logout-btn {
	background-color: #dc3545;
	color: white;
	border: none;
	padding: 5px 10px;
}

.content {
	padding: 20px;
}

.user-img {
	border-radius: 50%;
	width: 80px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar -->
			<div class="col-md-2 sidebar">
				<img src="https://via.placeholder.com/80" alt="User Image"
					class="user-img mb-3">
				<p>Bạn là Admin</p>
				<a href="#"><i class="bi bi-speedometer2"></i> Dashboard</a> <a
					href="#"><i class="bi bi-list-ul"></i> Quản lý danh mục</a> <a
					href="${pageContext.request.contextPath}/category/add"><i
					class="bi bi-plus-circle"></i> Thêm danh mục mới</a> <a
					href="${pageContext.request.contextPath}/category/list"><i
					class="bi bi-list-check"></i> Danh sách danh mục</a> <a href="#"><i
					class="bi bi-box-seam"></i> Quản lý sản phẩm</a> <a href="#"><i
					class="bi bi-qr-code"></i> Quản lý tài khoản</a>
			</div>

			<!-- Content -->
			<div class="col-md-10">
				<!-- Header -->
				<div
					class="header d-flex justify-content-between align-items-center">
					<h4>Dashboard</h4>
					<div>
						<span>Xin chào Phương Nguyên</span> <a
							href="${pageContext.request.contextPath}/logout"
							class="logout-btn">Đăng xuất</a>
					</div>
				</div>

				<!-- Main Content -->
				<div class="content">
					<h4 class="text-danger">Sửa danh mục</h4>
					<c:if test="${alert != null}">
						<p style="color: red">${alert}</p>
					</c:if>
					<form action="${pageContext.request.contextPath}/category/edit"
						method="post">
						<input type="hidden" name="cateId" value="${category.cateId}">
						<div class="mb-3">
							<label for="cateName" class="form-label">Tên danh mục</label> <input
								type="text" class="form-control" id="cateName" name="cateName"
								value="${category.cateName}" required>
						</div>
						<div class="mb-3">
							<label for="icons" class="form-label">Icon</label> <input
								type="text" class="form-control" id="icons" name="icons"
								value="${category.icons}">
						</div>
						<div>
							<label>Hình ảnh hiện tại:</label> <img
								src="${pageContext.request.contextPath}/images?fname=${category.icons}"
								style="width: 100px;">
						</div>
						<button type="submit" class="btn btn-primary">Cập nhật</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>