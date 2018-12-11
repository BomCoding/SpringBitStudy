<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>게시판프로젝트</title>
	<style>
		.form-inline{
			align:center;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
	<h1>FiveBoardProject</h1>
	<form action="#" class="form-inline">
		<div class="form-group">
			<select name="select" class="form-control">
			  <option value="all" selected="selected">전체게시판</option>
			  <option value="oneline">한줄게시판</option>
			  <option value="qna" >QnA게시판</option>
			  <option value="free" >자유게시판</option>
			  <option value="notice" >공지게시판</option>
			</select>
		</div>
		<div class="form-group">
			<label for="searchtext">검색 : </label>
			<input type="text" id="searchtext" name="search" class="form-control"/> 
			<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>			
		</div>
	</form>
	<br />
</div>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
