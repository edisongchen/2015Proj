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
			$.jBox.prompt('Hello jBox','jBox','warning',{closed:function (){alert('prompt is closed.');}});
		});
		$("#jinfo").click(function (){
			$.jBox.info('Hello jBox','jBox');
		});
		$("#jalert").click(function (){
			$.jBox.alert('Hello jBox','jBox');
		});
		$("#jsuccess").click(function (){
			$.jBox.success('Hello jBox','jBox');
		});
		$("#jerror").click(function (){
			$.jBox.error('Hello jBox','jBox');
		});
		$("#jconfirm").click(function (){
			$.jBox.info('Hello jBox','jBox');
		});
		$("#jwarning").click(function (){
			$.jBox.info('Hello jBox','jBox');
		});
		
		$("#jtip").click(function (){
			var submit = function (v, h, f) {
			    if (v == 'sure')
			        jBox.tip(v, 'info');
			    else 
			        jBox.tip(v, 'info');

			    return true; //close
			};

			$.jBox.confirm("确定吗？", "提示", submit,{buttons:{'确定':'sure','取消':'取消'}});
		});
	});
</script>
	
</head>
<body>
	<div id="jbox1">click  prompt type</div>
	<div id="jinfo">click  prompt info</div>
	<div id="jalert">extend  prompt: alert</div>
	<div id="jsuccess">extend  prompt success</div>
	<div id="jerror">extend  prompt error</div>
	<div id="jconfirm">extend  prompt confirm</div>
	<div id="jwarning">extend  prompt warning</div>
	<div id="jtip"> tip</div>
</body>
</html>