<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${ctxStatic}/libraries/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/libraries/jbox/jBox.min.js"></script>
<link href="${ctxStatic}/libraries/jbox/jBox.css" rel="stylesheet">

<script type="text/javascript">
//refrebce:http://stephanwagner.me/jBox/options
	$(document).ready(function (){
		 console.log("ffff");
		 console.log($('.tooltip').length);
		 $('.tooltip').jBox('Tooltip');
		new jBox('Modal', {
		    width: 300,
		    height: 200,
		    attach: $('#myModal'),
		    title: 'My Modal Window',
		    content: '<i>Hello there!</i>',
		    animation: 'slide'
		});
		
		//Events: onInit,onCreated,onOpen,onClose,onCloseComplete
		$("#myModal2").jBox('Modal',{
			onOpen:function (){
				this.setContent('Jbox is opening...')
			},
			onClose:function (){
				this.setContent('jBox is closing........');
				console.log('ready to close..');
			},
			onCloseComplete:function (){
				console.log('closed console.');
			},
			delayOpen: 500,
		    delayClose: 1000
		});
		//jBox type:Tooltip,Mouse,Modal,Confirm,Notice,Image
		//option: dimensions->width,heigth,minWidth,minHeight,max...
		var option={
			width:500,//dimensions
			height:300,
			
		}
		$("#myModal3").click(function (){
			console.log('fffffff');
			var myModel=new jBox('Modal',option);
			myModel.open();
		});
		//attaching jBox:attach,trigger,preventDefault
		new jBox('Confirm',{
			attach:$("#myModal4"),
			width:100,height:100,
			content:"fff",
			trigger:"click",
			preventDefault:true,
			confirmButton:'确定',
			cancelButton:"取消",
			confirm:function (){
				console.log('you click confirm');
			},
			cancel:function (){
				console.log('you click cancel .');
			},
			color:"red"
		});
	});
</script>

</head>
<body>
<div class="container">
	<a id="test" href="#">click</a>
<span class="tooltip" title="My tooltip ss">Hover me!</span>
<span class="tooltip" title="Another tooltip">Hover me!</span>
<div ><a href="#" id="myModal">Click me to open a modal window!</a></div>
<div ><a href="#" id="myModal2">modal Event listener</a></div>
<div ><a href="#" id="myModal3">test options</a></div>
<div ><a href="#" id="myModal4">test options</a></div>
</div>
</body>
</html>