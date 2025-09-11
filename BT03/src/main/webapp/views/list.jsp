<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý danh mục</title>
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

.table th, .table td {
	vertical-align: middle;
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
				<a href="#"><i class="bi bi-speedometer2"></i> Dashboard</a> 
				<a href="#"><i class="bi bi-list-ul"></i> Quản lý danh mục</a> 
				<a href="${pageContext.request.contextPath}/category/add"><i class="bi bi-plus-circle"></i> Thêm danh mục mới</a> 
				<a href="${pageContext.request.contextPath}/category/list"><i class="bi bi-list-check"></i> Danh sách danh mục</a>
			</div>

			<!-- Content -->
			<div class="col-md-10">
				<!-- Header -->
				<div class="header d-flex justify-content-between align-items-center">
					<h4>Dashboard</h4>
					<div>
						<span>Xin chào Phương Nguyên</span> 
						<a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng xuất</a>
					</div>
				</div>

				<!-- Main Content -->
				<div class="content">
					<h4 class="text-danger">Quản lý danh mục</h4>
				
					<div class="card">
						<div class="card-header bg-light">Danh sách danh mục</div>
						<div class="card-body">
							<div class="d-flex justify-content-between mb-3">
								<select class="form-select w-auto">
									<option>10 records per page</option>
								</select>
								<div class="d-flex">
									<input type="text" class="form-control me-2" placeholder="Search">
									<button class="btn btn-primary">Search</button>
								</div>
							</div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>STT</th>
										<th>Hình ảnh</th>
										<th>Tên danh mục</th>
										<th>Hành động</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="category" items="${categories}" varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>
												<!-- Cột Hình ảnh: Check icons và render đúng -->
												<c:choose>
													<c:when test="${not empty category.icons}">
														<img src="${pageContext.request.contextPath}/images?fname=${category.icons}"
														     alt="${category.cateName}"
														     class="img-thumbnail"
														     style="width: 50px; height: 50px; object-fit: cover;"
														     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images?fname=default.png'; this.alt='Không tải được ảnh';">
													</c:when>
													<c:otherwise>
														<span class="text-muted">(Không có ảnh)</span>
													</c:otherwise>
												</c:choose>
											</td>
											<td>${category.cateName}</td>
											<td>
												<a href="${pageContext.request.contextPath}/category/edit?cateId=${category.cateId}"
												   class="btn btn-primary btn-sm">Sửa</a> 
												<a href="${pageContext.request.contextPath}/category/delete?cateId=${category.cateId}"
												   class="btn btn-danger btn-sm"
												   onclick="return confirm('Xóa?')">Xóa</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>