<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<div>
		这是首页
		<a href="${ctx }/logout">退出</a>
	</div>
	<form id="inputForm" action="${ctx}/user/save" method="post">
		<input name="id" type="hidden" value="${user.id}"/>
		<input name="loginName" value="${user.loginName}"/>
		<input name="name" value="${user.name}"/>
		<input name="email" value="${user.email}"/>
		<input name="phone" value="${user.phone}"/>
		<input name="newPassword" value="${user.password}"/>
		<input type="submit" value="提交" />
	</form>
</body>
</html>