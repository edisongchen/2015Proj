<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function (){
		console.log('admin.....');
		var checkstr="1,3,5,6";
		var array=checkstr.split(",");
		for(var i in array){
			console.log(i);
			$("input[name="+i+"]").attr("checked","checked");
		}
	});
</script>
<style>
.container{margin:0 auto; border:1px solid red; width:300px;height:auto;}
.container>div{width:100%;height:80px;min-height:70px;border:1px solid blue;float:left;}
.titleDiv{width:20%;background:green;float:left;}
.content{width:78%;height:auto;float:left;}
lable{width:100px;height:20px;float:left;}
</style>
</head>
<body>
	<div class="container">
		<div>
			<div class="titleDiv">
				管理参数
			</div>
			<div class="content">
				<label><input type="checkbox" name="2" />管理参数</label>
				<label><input type="checkbox" name="1" />管理参数2</label>
				<label><input type="checkbox" name="3" />管理参数3</label>
				<label><input type="checkbox" name="4" />管理参数444</label>
				<label><input type="checkbox" name="5" />管理参数444</label>
				<label><input type="checkbox" name="2" />管理参数</label>
				<label><input type="checkbox" name="1" />管理参数2</label>
				<label><input type="checkbox" name="3" />管理参数3</label>
				<label><input type="checkbox" name="4" />管理参数444</label>
				<label><input type="checkbox" name="5" />管理参数444</label>
			</div>
		</div>
		
		<div>
			<div class="titleDiv">
				参数管理
			</div>
			<div class="content">
				<label><input type="checkbox" name="7" />管理参数444</label>
				<label><input type="checkbox" name="8" />管理参数444</label>
			</div>
		</div>
			
		<!-- <div>
			<div class="titleDiv">
				升级项管理
			</div>
			<div>
				<label><input type="checkbox" name="2" />管理参数</label>
				<label><input type="checkbox" name="2" />管理参数2</label>
				<label><input type="checkbox" name="2" />管理参数3</label>
				<label><input type="checkbox" name="2" />管理参数444</label>
				<label><input type="checkbox" name="2" />管理参数444</label>
				<label><input type="checkbox" name="2" />管理参数</label>
				<label><input type="checkbox" name="2" />管理参数2</label>
				<label><input type="checkbox" name="2" />管理参数3</label>
				<label><input type="checkbox" name="2" />管理参数444</label>
				<label><input type="checkbox" name="2" />管理参数444</label>
				<label><input type="checkbox" name="2" />管理参数</label>
				<label><input type="checkbox" name="2" />管理参数2</label>
				<label><input type="checkbox" name="2" />管理参数3</label>
				<label><input type="checkbox" name="2" />管理参数444</label>
				<label><input type="checkbox" name="2" />管理参数444</label>
			</div>
		</div> -->
	</div>
</body>
</html>