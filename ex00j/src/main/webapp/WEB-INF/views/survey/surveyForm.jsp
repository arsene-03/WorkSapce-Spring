<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사</title>
</head>
<body>
	<h2>설문조사</h2>
	<form method="POST">
		<c:forEach var="q" items="${questions}" varStatus="status">
			<p>
				${status.index+1}. ${q.title}<br>
				<c:if test="${q.choice}">
					<c:forEach var="option" items="${q.option}">
						<label>
							<input type="radio" name="responses[${status.index}]" value="${option}">${option}
						</label>
					</c:forEach>
				</c:if>
				
				<c:if test="${!q.choice}">
					<input type="text" name="responses[${status.index}]">
				</c:if>
			</p>
		</c:forEach>
				<p>
			<label>응답자 주거지 : 
			<input type="text" name="res.location">
			</label>
		</p>
		<p>
			<label>응답자 나이 : 
			<input type="text" name="res.age">
			</label>
		</p>
		<input type="submit" value="전송">
	</form>
	
	<!-- <form method="POST" action="survey">
		<p>
			1. 당신의 역할은?<br>
			<label><input type="radio" name="responses[0]" value="서버"/>서버개발자</label>
			<label><input type="radio" name="responses[0]" value="프론트"/>프론트개발자</label>
			<label><input type="radio" name="responses[0]" value="풀스택"/>풀스택개발자</label>
		</p>
		<p>
			2. 가장 많이 사용하는 개발도구는?<br>
			<label><input type="radio" name="responses[1]" value="Eclipse"/>Eclipse</label>
			<label><input type="radio" name="responses[1]" value="IntelliJ"/>IntelliJ</label>
			<label><input type="radio" name="responses[1]" value="NetBeans"/>NetBeans</label>
		</p>
		<p>
			3. 하고 싶은 말?<br>
			<input type="text" name="responses[2]">	responses[2] = 입력받은 value 값
		</p>
		<p>
			<label>응답자 주거지 : 
			<input type="text" name="res.location">
			</label>
		</p>
		<p>
			<label>응답자 나이 : 
			<input type="text" name="res.age">
			</label>
		</p>
		<input type="submit" value="전송">
	</form> -->
</body>
</html>