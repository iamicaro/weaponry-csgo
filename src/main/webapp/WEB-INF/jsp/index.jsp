<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Weaponry - CSGO</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,700'
	rel='stylesheet' type='text/css'>
<link href='custom.css' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="container">
		<h2>Weaponry - CSGO</h2>
		<div class="row">
		<c:forEach items="${armamento}" var="weaponry">
		<div class="media border p-3">
			<img src="${weaponry}" style="width:360px;">
			</div>
		</c:forEach>
		</div>
		</div>

	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>






