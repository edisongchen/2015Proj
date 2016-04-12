<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="${ctxStatic}/libraries/jquery-ztree/js/jquery.ztree.core-3.5.js"></script>
  <style>
  	/* zTree iconSkin 自定义图标 */
  .ztree li span.button.myIcon_ico_open{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/5.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  .ztree li span.button.myIcon_ico_close{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/6.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  
  .ztree li span.button.myIcon1_ico_open{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/7.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  .ztree li span.button.myIcon1_ico_close{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/7.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  .ztree li span.button.myIcon1_ico_docu{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/9.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  /* docu用于尾节点 */
  .ztree li span.button.myIcon2_ico_docu{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/8.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  
  	div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
	div#rMenu ul{width:100%;}
	div#rMenu ul li{
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #DFDFDF;
	}
  </style>
  <SCRIPT LANGUAGE="JavaScript">
  
  // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
  var setting = {
		  check:{enable:true},
		  data:{ simpleData:{enable:true, idKey:"id", pIdKey:"parent_id", rootPId:"1"} },
		  view:{showLine:false,fontCss:{color:"red"}},
		  callback:{
			  onRightClick:onRightClick
		  }
  };
  function onRightClick(event, treeId, treeNode){
	  //alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
	  if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTreeObj.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTreeObj.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
  }
  
  function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_del").hide();
		} else {
			$("#m_del").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

		$("body").bind("mousedown", onBodyMouseDown);
	}
  	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
  	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
  	var addCount = 1;
	function addTreeNode() {
		hideRMenu();
		var newNode = { name:"增加" + (addCount++)};
		if (zTreeObj.getSelectedNodes()[0]) {
			newNode.checked = zTreeObj.getSelectedNodes()[0].checked;
			zTreeObj.addNodes(zTreeObj.getSelectedNodes()[0], newNode);
		} else {
			zTreeObj.addNodes(null, newNode);
		}
	}
	function removeTreeNode() {
		hideRMenu();
		var nodes = zTreeObj.getSelectedNodes();
		if (nodes && nodes.length>0) {
			if (nodes[0].children && nodes[0].children.length > 0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==true){
					zTreeObj.removeNode(nodes[0]);
				}
			} else {
				zTreeObj.removeNode(nodes[0]);
			}
		}
	}
	
  // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
  var zNodes = [
  	{id:"1",parent_id:"",name:"test1",open:true,iconSkin:"myIcon"},
  	{id:"2",parent_id:"1",name:"test1-1",open:true,iconSkin:"myIcon1"},
  	{id:"3",parent_id:"1",name:"test1-2",open:true,iconSkin:"myIcon1"},
  	{id:"4",parent_id:"2",name:"test1-1-1",open:true,iconSkin:"myIcon2"},
  	{id:"5",parent_id:"3",name:"test1-2-1",open:true,iconSkin:"myIcon1"}
  	
  ];
  var zTreeObj;
  var rMenu;
  $(document).ready(function(){
     zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
     rMenu=$("#rMenu");
  });
  </SCRIPT>
 </HEAD>
<BODY>
<div>
   <ul id="treeDemo" class="ztree"></ul>
</div>
<div id="rMenu" style="top: 178px; left: 108px;visibility: hidden;">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加节点</li>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
	</ul>
</div>
</BODY>
</HTML>