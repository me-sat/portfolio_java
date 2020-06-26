<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>List</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<link rel="stylesheet" type="text/css" href="ButtonStyle.css">
<link href="https://fonts.googleapis.com/css?family=Caveat rel="stylesheet">

</head>
<body>
	<header>To Do List
		<form action="Main" method="get" >
			<input type="submit" name="button" value="To Do List" class="movepage-button">
		</form>
	</header>
	<div class="List-title">
		<p>--DONE LIST--</p>
	</div>
	<div class="outer">
		<div class="inner">
			<c:if test="${message !=null}"> <!-- messageの中がnullじゃなかったら -->
				<p class="message">${message}</p> <!-- message表示(messageの中がnullだったらfalseになるから一切表示されない) -->
			</c:if>
			<c:forEach var="i" items="${list}">
				<div class="ToDo finish">${i.DO}
					<div class="DeadLine2">DeadLine:${i.DEADLINE}</div>
					<form action="Finish" method="post">
						<input type="submit" name="button" value="Return" class="return-button"/>
						<input type="submit" name="button" value="Delete" class="delete-button"/>
						<input type="hidden" name="id" value="${i.id}">
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
		
		
</body>
</html>

