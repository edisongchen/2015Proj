<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script type="text/javascript">
	$(document).ready(function (){
		console.log('test success.....');
	});
</script>
</head>
<body>
	<div>
		<form id="loginForm" action="${ctx}/login" method="post">
			<input type="text" name="username" placeHolder="用户名" />
			<input type="text" name="password" placeHolder="密码" />
			<input type="submit" value="登录"/>
		</form>
	</div>
</body>
</html>