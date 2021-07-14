<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
<h2><spring:message code="member.info"/></h2>
<!-- 스프링 mvc 커스텀 태그를 사용한 방법-->
	<form:form action="step3" commandName="formData">
		<p>
			<label><spring:message code="email"/>:<br>
			<form:input path="email"/>
				<%-- <input type="text" name="email" id="email" value="${formData.email}"> --%>
			<form:errors path="email"/>
			</label>
		</p>
		<p>
			<label><spring:message code="name"/>:<br>
			<form:input path="name"/>
				<%-- <input type="text" name="name" id="name" value="${formData.name}"> --%>
			<form:errors path="name"/>
			</label>
		</p>
		<p>
			<label><spring:message code="password"/>:<br>
			<form:password path="password"/>
				<!-- <input type="password" name="password" id="password"> -->
				<form:errors path="password"/>
			</label>
		</p>
		<p>
			<label><spring:message code="password.confirm"/>:<br>
			<form:password path="confirmPassword"/>
				<!-- <input type="password" name="confirmPassword" id="confirmPassword"> -->
				<form:errors path="confirmPassword"/>
			</label>
		</p>
		<input type="submit" value="<spring:message code="register.btn"/>">
	</form:form>
<%-- 일반적인 방법 
	<form action="step3" method="post">
	<p>
		<label>이메일:<br>
			<input type="text" name="email" id="email" value="${formData.email}">
		</label>
	</p>
	<p>
		<label>이름:<br>
			<input type="text" name="name" id="name" value="${formData.name}">
		</label>
	</p>
	<p>
		<label>암호:<br>
			<input type="password" name="password" id="password">
		</label>
	</p>
	<p>
		<label>암호 확인:<br>
			<input type="password" name="confirmPassword" id="confirmPassword">
		</label>
	</p>
	<input type="submit" value="가입 완료">

</form> --%>
</body>
</html>