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
	$(document).ready(function (){
		$("#jbox1").click(function (){
			console.log('jbox1.click/////');
			$.jBox('ccc',{title:'test jquery jbox2.3',bottomText:'testbuttom text'});
		});
		$("#jbox2").click(function (){
			var info = 'jQuery jBox<br /><br />版本：v2.0<br />日期：2011-7-24<br />';
			info += '官网：<a target="_blank" href="http://kudystudio.com/jbox">http://kudystudio.com/jbox</a>';
			$.jBox.info(info,{title:'jboxInfo'});
		});
		$("#jbox3").click(function (){
			//TODO 以后加了异步处理的时候多看看//jboxGETest.jsp?id=1
			$.jBox("get:jboxGETest.jsp?id=1",{title:'GET'});
		});
		$("#jbox4").click(function (){
			$.jBox("iframe:http://www.baidu.com", {
			    title: "百度一下",
			    width: 800,
			    height: 350,
			    buttons: { '关闭': true }
			});
		});
	});
</script>

</head>
<body>
<div id="container">
	<div id="jbox1">jquery-jbox入门例子</div>
	<div id="jbox2">jbox info</div>
	<div id="jbox3">jbox get</div>
	<div id="jbox4">jbox frame</div>
</div>
</body>
</html>