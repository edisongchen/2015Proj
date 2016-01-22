$(function (){
	require.config({
        paths: {
            echarts: '/WEBDemo/static/libraries/echarts/dist'
        }
    });
});

function show_line(){
//这里只列出option中一些常用的参数
option = {
		backgroundColor:'#2cb5ae',//echarts的背景色
		color:["#122581","#128181","#12813e"],//数据系列的背景色 legend大于这个集合将会循环使用
	    animation:true,
		title : {
	        text: 'line test',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['最高气温','legend2']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} °C'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'最高气温',
	            type:'line',
	            data:[11, 11, 15, 13, 12, 13, 10],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            }
	        },
	        {
	        	name:'legend2',
	        	type:'line',
	        	data:[1,1,5,3,2,3,10]
	        }
	    ]
	};
	
	require(
		[
	     'echarts',
	     'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
	     'echarts/chart/bar'
	     ],
		 function (ec) {
		     var myChart = ec.init(document.getElementById('line-charts'));
		     myChart.setOption(option);
		 }
	);
	
}