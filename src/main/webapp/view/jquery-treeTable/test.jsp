<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${ctxStatic }/libraries/treeTable/jquery.treeTable.js" type="text/javascript"> </script>
<script type="text/javascript">
     $(function(){
         var option = {
             //theme:'vsStyle',//默认是default
             expandLevel :1,
             beforeExpand : function($treeTable, id) {
             	//haschild 属性可以判断是否具有子节目，并展开树
                 //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                 if ($('.' + id, $treeTable).length) { return; }
                 //这里的html可以是ajax请求
                 var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
                          + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

                 $treeTable.addChilds(html);
                 $('#treeTable1').treeTable();
             },
             onSelect : function($treeTable, id) {
                 window.console && console.log('onSelect:' + id);
             }

         };
         $('#treeTable1').treeTable(option);
     });
</script> 
</head>
<body>
	<table id="treeTable1" style="width: 100%">
                <tr>
                    <td style="width: 200px;">
                        标题</td>
                    <td>
                        内容</td>
                </tr>
                <tr id="1">
                    <td><span controller="true">1</span></td>
                    <td>内容</td>
                </tr>
                <tr id="2" pid="1">
                    <td><span controller="true">2</span></td>
                    <td>内容</td>
                </tr>
                <tr id="3" pid="2">
                    <td>3</td>
                    <td>内容</td>
                </tr>
                <tr id="4" pid="2">
                    <td>4</td>
                    <td>内容</td>
                </tr>
                <tr id="5" pid="4">
                    <td>4.1</td>
                    <td>内容</td>
                </tr><!--haschild="true"  -->
                <tr id="6" pid="1" >
                    <td><span controller="true">5</span></td>
                    <td>注意这个节点是动态加载的</td>
                </tr>
                <tr id="7">
                    <td>8</td>
                    <td>内容</td>
                </tr>
            </table>
</body>
</html>