<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Finish</title>
</head>
<body>
		<p>--DONE LIST--</p>
		<c:if test="${message !=null}"> <!-- messageの中がnullじゃなかったら -->
				<p class="message">${message}</p> <!-- message表示(messageの中がnullだったらfalseになるから一切表示されない) -->
		</c:if>
		<c:forEach var="i" items="${list}">
			<div class="ToDo">${i.DO}
			<div class="DeadLine">DeadLine:${i.DEADLINE}</div>
				<form action="Finish" method="post">
					<input type="submit" name="button" value="Return"/>
					<input type="submit" name="button" value="Delete"/>
					<input type="hidden" name="id" value="${i.id}">
				</form>
			</div>
		</c:forEach>
		<form action="Main" method="get" >
			<input type="submit" name="button" value="To Do List">
		</form>
</body>
</html>

