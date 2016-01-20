$(function() {
	//show_dyn_data();
	//show_pie();
	require.config({
        paths: {
            echarts: '/WEBDemo/static/libraries/echarts/dist'
        }
    });
});

function show_dyn_data(){
	var option = {
		    title : {
		        text: '动态数据',
		        subtext: '纯属虚构'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['最新成交价']
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
		    dataZoom : {
		        show : false,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : true,
		            data : [1,2,3,5,6,7,8,9,10]
		        }
		    ],
		    yAxis : [
		        {
		        	type:'value',
		        	min:0,
		        	max:20
		        }
		    ],
		    series : [
		        
		        {
		            name:'最新成交价',
		            type:'line',
		            data:[1,2,3,4,5,6,7,8,9,10]
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
					     var myChart = ec.init(document.getElementById('dyn-chart'));
					     var lastData = 11;
							var axisData;
							clearInterval(timeTicket);
							var timeTicket = setInterval(function (){
							    lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
							    lastData = lastData.toFixed(1) - 0;
							    console.log("ld:"+lastData);
//							    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
							    axisData = Math.floor(Math.random()*10);
							    console.log('xaxis:'+axisData);
							    // 动态数据接口 addData
							    myChart.addData([
							        [
							            0,        // 系列索引
							            lastData, // 新增数据
							            false,    // 新增数据是否从队列头部插入
							            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							            axisData  // 坐标轴标签
							        ]
							    ]);
							}, 4000);
					     myChart.setOption(option);
					 }
				);
}

function show_pie(){
	console.log("//////");
	option = {
 		   title: {
 		       text: "对数轴示例",
 		       x: "center"
 		   },
 		   tooltip: {
 		       trigger: "item",
 		       formatter: "{a} <br/>{b} : {c}"
 		   },
 		   legend: {
 		       x: 'left',
 		       data: ["2的指数", "3的指数"]
 		   },
 		   xAxis: [
 		       {
 		           type: "category",
 		           name: "x",
 		           splitLine: {show: false},
 		           data: ["一", "二", "三", "四", "五", "六", "七", "八", "九"]
 		       }
 		   ],
 		   yAxis: [
 		       {
 		           type: "log",
 		           name: "y"
 		       }
 		   ],
 		    toolbox: {
 		       show: true,
 		       feature: {
 		           mark: {
 		               show: true
 		           },
 		           dataView: {
 		               show: true,
 		               readOnly: true
 		           },
 		           restore: {
 		               show: true
 		           },
 		           saveAsImage: {
 		               show: true
 		           }
 		       }
 		   },
 		   calculable: true,
 		   series: [
 		       {
 		           name: "3的指数",
 		           type: "bar",
 		           data: [1, 3, 9, 27, 81, 247, 741, 2223, 6669]

 		       },
 		       {
 		           name: "2的指数",
 		           type: "bar",
 		           data: [1, 2, 4, 8, 16, 32, 64, 128, 256]

 		       }
 		   ]
 		};
	 require(
		[
	     'echarts',
	     //'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
	     'echarts/chart/bar'
	     ],
		 function (ec) {
		     var myChart = ec.init(document.getElementById('pie-chart'));
		     myChart.setOption(option);
		 }
	);
}

/**
 * 需要实现横轴刻度不一定
 */
function show_bar(){
	option = {
		    title : {
		        text: '降水量',
		        subtext: '纯属虚构'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['降水量']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'降水量',
		            type:'line',
		            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
		            /* markPoint : {
		                data : [
		                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
		                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name : '平均值'}
		                ]
		            }*/
		        }
		    ]
		};
	 require(
				[
			     'echarts',
			     //'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
			     'echarts/chart/line'
			     ],
				 function (ec) {
				     var myChart = ec.init(document.getElementById('bar-chart'));
				     myChart.setOption(option);
				 }
			);
}