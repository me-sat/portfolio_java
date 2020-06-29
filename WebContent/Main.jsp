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
<link rel="stylesheet" type="text/css" href="calendar.css">
<link href="https://fonts.googleapis.com/css?family=Caveat rel="stylesheet">
<script type="text/javascript" src="calendar.js"></script>
</head>
<body>

	<header>To Do List
		<form action="Finish" method="get" >
			<input type="submit" name="button" value="Done List" class="movepage-button"/>
		</form>	
	</header>
	
	
	<div class="List-title">
		<p>--TO DO LIST--</p>
	</div>
		
	<div class="container">
	
		<div class="main-container box">
			<form action="Main" method="post" >
				<c:if test="${message2 != null}"> <!-- messageの中がnullじゃなかったら -->
					<p class="message2">${message2}</p> <!-- message表示(messageの中がnullだったらfalseになるから一切表示されない) -->
				</c:if>
				<p>things to do</p>
				<textarea name="todo" rows="4" class="textbox-radius" placeholder="What do you wanna do？"></textarea>
				<p>DeadLine</p>
				<p><input type="date" name="deadline" class="button-date"/></p>
				<p><input type="submit" name="button" value="Add List" class="button-AddList"/></p>
			</form>
		</div>
		
		<div class="List-contants box">
			<c:if test="${message != null}"> <!-- messageの中がnullじゃなかったら -->
				<p class="message">${message}</p> <!-- message表示(messageの中がnullだったらfalseになるから一切表示されない) -->
			</c:if>
			<c:forEach var="i" items="${list}">
				<div class="ToDo">
					<table class="hyou">
						<tr>
							<td>
								${i.DO}
								<div class="DeadLine">DeadLine:${i.DEADLINE}</div>
							</td>
							
							<td>
								<form action="Main" method="post">
									<input type="submit" name="button" value="Done"  class="done-button"/>
									<input type="hidden" name="id" value="${i.id}">
							 	</form>
							</td>
						</tr>
					</table>
					
				 		
						<!--Delete ボタン-->	
						<!-- <input type="submit" name="button" value="Delete" class="delete-button"/> -->
				</div>
			</c:forEach>
		</div>	

		<div class="cal box">
			<button id="prev" type="button" class="Before-button">Before</button>
			<button id="next" type="button" class="Next-button">Next</button>
			<div id="calendar"></div>
		</div>
	</div>
	
	
</body>
</html>