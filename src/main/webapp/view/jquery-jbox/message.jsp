<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/libraries/jquery-jbox/Skins/Blue/jbox.css"/>
<script type="text/javascript" src="${ctxStatic}/libraries/jquery-jbox/jquery.jBox-2.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/libraries/jquery-jbox/i18n/jquery.jBox-zh-CN.min.js"></script>
<script type="text/javascript">
	$(function (){
		$("#jbox1").click(function (){
			$.jBox.messager('Hello jBox','jBox','warning');
		});
	});
</script>
	
</head>
<body>
	<div id="jbox1">click  messager</div>
</body>
</html>