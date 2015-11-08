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
			$.jBox('html:test',
					{title:'test jquery jbox2.3',
					 border:1,
					 bottomText:'testbuttom text',
					 //timeout:3000,
					 showType:'slide',
					 buttons:{'bt1':'b1','bt2':'b2','bt3':'b3'},
					 buttonsFocus:1
					 });
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
		$("#jbox5").click(function(){
			var content = {
			    state1: {
			        content: '状态一'+"<input type='text'/>",
			        buttons: { '下一步': 1, '取消': 0 },
			        buttonsFocus: 0,
			        submit: function (v, h, f) {
			            if (v == 0) {
			            	return true;
			            }// close the window}
			            else {
			            	$.jBox.nextState(); //go forward// 或 $.jBox.goToState('state2')
			            }
			            return false;
			        }
			    },
			    state2: {
			        content: '状态二，请关闭窗口哇：）',
			        buttons: { '上一步': 1, '取消': 0 },
			        buttonsFocus: 0,
			        submit: function (v, h, f) {
			            if (v == 0) {
			                return true; // close the window
			            } else {
			                $.jBox.prevState() //go back
			                // 或 $.jBox.goToState('state1');
			            }
			            return false;
			        }
			    }
			};
			$.jBox(content,{title:'state-next'});
		});
		
		$("#jbox6").click(function (){
			var html = "<div style='padding:10px;'>输入姓名：<input type='text' id='yourname' name='yourname' /></div>";
			var submit = function (v, h, f) {
				/* 点击状态按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，
				 * h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 
				 */
				if (f.yourname == '') {//找的是name
			        $.jBox.tip("请输入您的姓名。", 'error', { focusId: "yourname" }); // 关闭设置 yourname 为焦点
			        return false;
			    }
			    $.jBox.tip("你叫：" + h.find("#yourname").val());
			    //$.jBox.tip("你叫：" + h.find(":input[name='yourname']").val());
			    return true;
			};
			$.jBox(html, {id:'111' , title: "你叫什么名字？", submit: submit });
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
	<div id="jbox5">jbox nextState</div>
	<div id="jbox6">jbox print tip</div>
</div>
</body>
</html>