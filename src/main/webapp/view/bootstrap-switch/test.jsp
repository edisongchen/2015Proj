<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap Switch · Turn checkboxes and radio buttons in toggle switches</title>
    <link href="${ctxStatic}/libraries/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctxStatic}/libraries/bootstrap-switch/css/bootstrap3/bootstrap-switch.css" rel="stylesheet">
	<script src="${ctxStatic}/libraries/jquery/jquery-1.11.0.js"></script>
	<script src="${ctxStatic}/libraries/jquery/jquery-migrate-1.2.1.js"></script>
	<script src="${ctxStatic}/libraries/bootstrap-switch/js/bootstrap-switch.js"></script>
	<script src="${ctxStatic}/libraries/bootstrap/js/bootstrap.min.js"></script>
  </head>
    <script>
    $(function() {
    	console.log("load success");
      $('[type="checkbox"]').bootstrapSwitch();
      
      $("#setstate").toggle(function (){
    	  $('input[name="my-state"]').bootstrapSwitch('size', 'large', true);
    	  console.log('setstate');
      },function (){
    	 $('input[name="my-state"]').bootstrapSwitch('state',false,true);
      });
      /* $('[type="checkbox"]').on('switchChange.bootstrapSwitch', function(event, state) {
    	  console.log(this); // DOM element
    	  console.log(event); // jQuery event
    	  console.log(state); // true | false   		+"<option value=\"3\" selected='false'>3</option>"
    		+"<option value=\"4\" selected='true'>4</option>"
    	}); */
    	
    	$("#appendDom").click(function (){
    		
    		var html="<select>"
    		+"<option value=\"1\" "+test(1,2)+">1</option>"
    		+"<option value=\"2\" "+test(2,2)+">2</option>"
    		+"<option value=\"3\" "+ test(3,2)
    		+">3</option>"
    		+"</select>";
    		
    		console.log(html);
    		console.log(test());
    		$(".testDiv").append(html);
    	});
    	
    	$("#testDis1").toggle(function (){
    		console.log($("#testDis").bootstrapSwitch('readonly'));
    		$("#testDis").bootstrapSwitch('readonly',false);
    		$("#testDis").bootstrapSwitch('state',false);
    		$("#testDis").bootstrapSwitch('readonly',true);
    	},function (){
    		console.log($("#testDis").bootstrapSwitch('readonly'));
    		$("#testDis").bootstrapSwitch('readonly',false);
    		$("#testDis").bootstrapSwitch('state',true);
    		$("#testDis").bootstrapSwitch('readonly',true);
    	})
    })
    
    function test(opt,sel){
    	if(opt==sel){
    		return 'selected';
    	}else{
    		return '';
    	}
    }
    </script>
  <body>
    <div class="switch switch-large">
	    <input type="checkbox" data-size="small" checked />
	</div>
	
	<div class="switch">
	    <input type="checkbox" data-size="large" data-on-text="主机状态" data-off-text="主备机状态" checked />
	</div>
	
	<div class="switch">
		<input type="button" id="setstate" value="setstate"/>
	    <input type="checkbox" name="my-state" />
	</div>
	
	<input type="checkbox"  checked />
	<hr/>
	<input type="button" id="appendDom" value="appendDom"/>
	<div class="testDiv">
	test div:
	</div>
	<select>
		<option value="1" >1</option>
		<option value="2" >2</option>
		<option value="3" >3</option>
	</select>
	
	<input id="testDis1" type="button" value="disabeld blow"/>
	<input id="testDis"  type="checkbox" data-on-text="o" data-off-text="x" >
  </body>
</html>