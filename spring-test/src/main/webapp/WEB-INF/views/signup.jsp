<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원가입</title>
	<script src="//code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<h1>회원가입</h1>
	<form method="post" action="<%=request.getContextPath()%>/signup" id="form">
		<div>
			아이디<input type="text" name="id" id="id">
			<button id="dup" type="button">아이디 중복체크</button>
		</div>
		<div>
			비밀번호<input type="password" name="pw">
		</div>
		<div>
			이메일<input type="email" name="email">
		</div>
		<div>
			<input type="radio" value="male" name="gender" id="male" checked>
			<label for="male">남성</label>
			<input type="radio" value="female" name="gender" id="female" checked>
			<label for="female">여성</label>
		</div>
		<button type="button" id="btnOk">회원가입</button>
	</form>
	<script type="text/javascript">
    var dup = 0;
    $('#dup').click( function() {
        var id = "";
        id = $('#id').val();
        $.ajax({
            url: 'http://localhost:8080/springtest/signup/dup',
            type:'POST',
            data:id,
            dataType: 'json',
            contentType:"application/json; charset=UTF-8",
            success: function(data) {
            	console.log(data.dup);
            	if(data.dup){
            		dup = 1;
            		alert('이미 사용중인 아이디입니다.');
            	}else{
            		dup = -1;
            		alert('사용 가능한 아이디입니다.');
            	}
            }
        });
    })
    $("#btnOk").click(function(){
    	if(dup == 0 ){
    		alert("아이디 중복 체크를 하세요");
    		return false;
    	}
    	if(dup == 1 ){
    		alert("중복된 아이디입니다. 다른 아이디를 입력하세요");
    		return false;
    	}
    	$('#form').submit();
    	return true;
    })
	</script>
</body>
</html>
