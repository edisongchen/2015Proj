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