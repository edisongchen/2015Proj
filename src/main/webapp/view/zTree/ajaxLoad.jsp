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
  <script type="text/javascript" src="${ctxStatic}/libraries/jquery-ztree/js/jquery.ztree.excheck-3.5.js"></script>
  <script type="text/javascript" src="${ctxStatic}/libraries/jquery-ztree/js/jquery.ztree.exedit-3.5.js"></script>
  <SCRIPT LANGUAGE="JavaScript">
    var treeObj=null;
	var setting = {
		view:{ showLine : false },
		async: {
            enable: true,
            type: "post",
            contentType: "application/json",
            url:"${ctx}/springmvc/ajax/tree/next",
            autoParam:["id"],
            //dataType:"json"
            dataFilter:filter
    	},
		data:{ simpleData:{enable:true, idKey:"id", pIdKey:"parent_id", rootPId:null} },
    	callback: {
			beforeAsync: beforeAsync,
			onAsyncSuccess: onAsyncSuccess,
			onAsyncError: onAsyncError,
			beforeClick:beforeClick
		}
	};
	
	function beforeClick(treeId, treeNode){
		doPost(treeNode);
        return false;
	}
	function doPost(treeNode){
		$.ajax({
			
			type:"post",
			data:{"id":treeNode.id},
			dataType:"json",
			url:"${ctx}/springmvc/ajax/tree/next",
			success:function (data){
				console.log('//'+data);
				for(var i=0;i<data.length;i++){
					treeObj.addNodes(treeNode, data[i]);
				}
			},
			error:function (data){
				console.log('error:'+data);
			}
		});
	}
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	function beforeAsync() {
		console.log('////');
	}
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		//curAsyncCount--;
		console.log('async success');
	}
	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		console.log('error');
	}
	var zNodes = [
		{"id":"1","name":"level1","parent_id":"0",open:false},
		{"id":"2","name":"level2","parent_id":"0",open:false},
		{"id":"3","name":"level3","parent_id":"1",open:false}
	];
	$(function (){
		treeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//treeObj.expandAll(true);
	});
	</script>
<BODY>
<div>
   <ul id="treeDemo" class="ztree"></ul>
</div>
</BODY>
</HTML>