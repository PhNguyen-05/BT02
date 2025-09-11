<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thêm danh mục</title>
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
					class="bi bi-list-check"></i> Danh sách danh mục</a>

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
					<h4 class="text-danger">Thêm danh mục mới</h4>
					<c:if test="${alert != null}">
						<p style="color: red">${alert}</p>
					</c:if>
					<form action="${pageContext.request.contextPath}/category/add"
						method="post" enctype="multipart/form-data">
						<div class="mb-3">
							<label for="cateName" class="form-label">Tên danh mục</label> <input
								type="text" class="form-control" id="cateName" name="cateName"
								required>
						</div>
						<div class="mb-3">
							<label for="icons" class="form-label">Icon (chọn file
								hình ảnh)</label> <input type="file" class="form-control" id="icons"
								name="icons" accept="image/*">
						</div>
						<button type="submit" class="btn btn-primary">Thêm</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>