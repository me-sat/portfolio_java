<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Main</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>
	<header>To Do List
		<form action="Finish" method="get" >
			<input type="submit" name="button" value="Done List" class="movepage-button"/>
		</form>	
	</header>
	<form action="Main" method="post" >
		<div class="main-container">
			<textarea name="todo" rows="4" class="textbox-radius" placeholder="What do you wanna do？"></textarea>
			<p><input type="date" name="deadline" class="button-date"/></p>
			<p><input type="submit" name="button" value="Add List" class="button-AddList"/></p>
		</div>
	</form>
	<div class="List-title"><p>--TO DO LIST--</p></div>
	<div class="outer">
		<div class="inner">
			<c:if test="${message !=null}"> <!-- messageの中がnullじゃなかったら -->
					<p class="message">${message}</p> <!-- message表示(messageの中がnullだったらfalseになるから一切表示されない) -->
			</c:if>
			<div class="List-contants">
				<c:forEach var="i" items="${list}">
						<div class="ToDo">${i.DO}
							<div class="DeadLine">DeadLine:${i.DEADLINE}</div>
							<form action="Main" method="post">
								<input type="submit" name="button" value="done"  class="done-button"/>
								<input type="submit" name="button" value="Delete" class="delete-button"/>
								<input type="hidden" name="id" value="${i.id}">
							</form>
						</div>
				</c:forEach>
			</div>	
		</div>
	</div>
</body>
</html>