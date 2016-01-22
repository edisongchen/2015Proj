<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> echarts demo</title>
<style type="text/css">
.style1{width:500px;height:400px;border:1px solid red;float:left;}
</style>
</head>
<body>
	<li>
		<a href="#" onclick="show_line()">click line</a>
	</li>
	
	<div id="line-charts" class="style1">
	
	</div>
	
	<script src="${ctxStatic}/libraries/echarts/dist/echarts.js"></script>
	<script  src="${ctxStatic}/script/report/baseCharts.js"></script>
</body>
</html>