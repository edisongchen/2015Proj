<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/include/taglib.jsp"%>
<%@ include file="/view/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
        // 获取终端的相关信息
        var Terminal = {
            // 辨别移动终端类型
            platform : function(){
                var u = navigator.userAgent, app = navigator.appVersion;
                return {
                    // android终端或者uc浏览器
                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
                    // 是否为iPhone或者QQHD浏览器
                    iPhone: u.indexOf('iPhone') > -1 ,
                    // 是否iPad
                    iPad: u.indexOf('iPad') > -1
                };
            }(),
            // 辨别移动终端的语言：zh-cn、en-us、ko-kr、ja-jp...
            language : (navigator.browserLanguage || navigator.language).toLowerCase()
        }
 
        // 根据不同的终端，跳转到不同的地址
        var msg = '除android,ios,ipad的其它类型';
        if(Terminal.platform.android){
        	msg = '你的Android APP对应下载地址：apk文件地址';
        }else if(Terminal.platform.iPhone){
        	msg = '你的iPhone APP对应下载地址：APP Store地址';
        }else if(Terminal.platform.iPad){
        	msg = 'opad 下载地址';
        }
 
        alert(msg);
    </script>
</head>
<body>
	<h2>刷新页面 ，测试 客户端类型</h2>
</body>
</html>