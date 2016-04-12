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
  .ztree li span.button.myIcon1_ico_docu{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/7.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  .ztree li span.button.myIcon2_ico_docu{margin-right:2px;background:url(${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/8.png) no-repeat scroll 0 0 transparent;vertical-align:middle;}
  </style>
  <SCRIPT LANGUAGE="JavaScript">
   var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {view:{showLine:false}};
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   var zNodes = [
   {name:"test1",icon:"${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/7.png", open:true, children:[
      {name:"test1_1"}, {name:"test1_2"}]},
   {name:"test2",iconOpen:"${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/1_open.png",
    	  iconClose:"${ctxStatic}/libraries/jquery-ztree/css/zTreeStyle/img/diy/1_close.png", open:true, children:[
      {name:"test2_1"}, {name:"test2_2"}]},
   {name:"iconSkin图标",iconSkin:"myIcon",open:true,children:[
            {name:"自定义图标1",iconSkin:"myIcon1"},
            {name:"自定义图标2",iconSkin:"myIcon2"}                             
        ]
    	  
   }
   ];
   $(document).ready(function(){
      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
   });
  </SCRIPT>
 </HEAD>
<BODY>
<div>
   <ul id="treeDemo" class="ztree"></ul>
</div>
</BODY>
</HTML>