$(function() {
	show_dyn_data();
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
		        data:['最新成交价', '预购队列']
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
		            data : (function (){
		                var now = new Date();
		                var res = [];
		                var len = 10;
		                while (len--) {
		                	var s1=now.toLocaleTimeString().replace(/^\D*/,'');
		                    res.unshift(s1);
		                    now = new Date(now - 2000);
		                    console.log(s1);
		                }
		                return res;
		            })()
		        },
		        {
		            type : 'category',
		            boundaryGap : true,
		            data : (function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push(len + 1);
		                }
		                return res;
		            })()
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            scale: true,
		            name : '价格',
		            boundaryGap: [0.2, 0.2]
		        },
		        {
		            type : 'value',
		            scale: true,
		            name : '预购量',
		            boundaryGap: [0.2, 0.2]
		        }
		    ],
		    series : [
		        {
		            name:'预购队列',
		            type:'bar',
		            xAxisIndex: 1,
		            yAxisIndex: 1,
		            data:(function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push(Math.round(Math.random() * 1000));
		                }
		                return res;
		            })()
		        },
		        {
		            name:'最新成交价',
		            type:'line',
		            data:(function (){
		                var res = [];
		                var len = 10;
		                while (len--) {
		                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
		                }
		                return res;
		            })()
		        }
		    ]
		};
		
		
		require.config({
	        paths: {
	            echarts: '/WEBDemo/static/libraries/echarts/dist'
	        }
	    });
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
							//clearInterval(timeTicket);
							var timeTicket = setInterval(function (){
							    lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
							    lastData = lastData.toFixed(1) - 0;
							    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
							    
							    // 动态数据接口 addData
							    myChart.addData([
							        [
							            0,        // 系列索引
							            Math.round(Math.random() * 1000), // 新增数据
							            true,     // 新增数据是否从队列头部插入
							            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							        ],
							        [
							            1,        // 系列索引
							            lastData, // 新增数据
							            false,    // 新增数据是否从队列头部插入
							            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							            axisData  // 坐标轴标签
							        ]
							    ]);
							}, 2100);
					     myChart.setOption(option);
					 }
				);
}

function show_pie(){
	console.log("//////");
	require.config({
        paths: {
            echarts: '/WEBDemo/static/libraries/echarts/dist'
        }
    });
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
 		           type: "line",
 		           data: [1, 3, 9, 27, 81, 247, 741, 2223, 6669]

 		       },
 		       {
 		           name: "2的指数",
 		           type: "line",
 		           data: [1, 2, 4, 8, 16, 32, 64, 128, 256]

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
		     var myChart = ec.init(document.getElementById('pie-chart'));
		     myChart.setOption(option);
		 }
	);
}