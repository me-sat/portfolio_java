<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Specific.css">
<link rel="stylesheet" type="text/css" href="ButtonStyle.css">
<link rel="stylesheet" type="text/css" href="calendar.css">
<script type="text/javascript" src="calendar.js"></script>

</head>
<body>
	<header>
		To Do List
		<form action="Finish" method="get">
			<input type="submit" name="button" value="Done List"
				class="movepage-button" />
		</form>
		<form action="Main" method="get">
			<input type="submit" name="button" value="All List"
				class="backpage-button" />
		</form>
	</header>
	<div class="container">
		<p id="selectedDate" class="List-title">${spDate}</p>
			<div class="main-container box">
				
			</div>
		
			<div class="List-contants box">
				<c:if test="${message != null}">
					<!-- messageの中がnullじゃなかったら -->
					<p class="message">${message}</p>
					<!-- message表示(messageの中がnullだったらfalseになるから一切表示されない) -->
				</c:if>
				<c:forEach var="i" items="${list}">

					<table class="hyou">
						<tr>
							<td>${i.DO}
								<div class="DeadLine">DeadLine:${i.DEADLINE}</div>
							</td>

							<td>
								<form action="Main" method="post">
									<input type="submit" name="button" value="Done"
										class="done-button" /> <input type="hidden" name="id"
										value="${i.id}">
								</form>
							</td>
						</tr>
					</table>
				</c:forEach>
			</div>

			<div class="cal box">
				<button id="prev" type="button" class="Before-button">Prev</button>
				<button id="next" type="button" class="Next-button">Next</button>
				<div id="calendar"></div>
			</div>
		</div>

</body>
</html>