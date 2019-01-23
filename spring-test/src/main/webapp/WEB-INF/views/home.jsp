<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.css">
</head>
<body>
<h1>
	환영합니다!  
</h1>
<%-- 컨트롤러에서 전달한 데이터를 받기 위해 ${컨트롤러에서 전달한이름}을 사용한다. --%>
<P>  로그인 하시면 다양한 서비스를 이용하실 수 있습니다.</P>
<%-- jsp에서 컨트롤러로 데이터를 전달하려면 form태그를 이용 --%>
<%--<form method="post" action="/spring/">이렇게 이름을 넣어줄 수 있지만 이름 바뀔때마다 바꿔줘야 하므로 하기와 같이함--%>
<form method="post" action="<%=request.getContextPath() %>/" 
	style="<c:if test="${user != null }">display:none;</c:if>">
	<%-- 전달하려는 데이터가 있는 태그의 속성 name을 입력 --%>
	아이디<input type="text" name="id"><br>
	비밀번호<input type="password" name="pw"><br>
	<button class="btn btn-primary">로그인</button>
</form>	
<a href="<%=request.getContextPath() %>/signup">아직 회원이 아니라면 회원가입창으로 이동</a>
</body>
</html>
