<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>first springMVC</title>
<script type="text/javascript">
	$(function (){
		console.log("loadded...");
		$("#testSend").click(function (){
			console.log("send....");
			var param ={};
			param.id="10";
			param.method="getUser";
			param.jsonrpc="2.0";
			console.log(param);
			$.ajax({
				url:"${ctx}/testservlet/test2",
				data:JSON.stringify(param),
				type:"POST",
				dataType:"json",
				success:function (data){
					console.log('success:'+data);
				},
				error:function (data){
					console.log('error:'+data);
				}
			});
		});
	})
</script>
</head>
<body>
	<input type="submit" value="submit" id="testSend"/>
</body>
</html>