/**
 * 
 */
package com.proj.test.jsonrpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
public class TestClent {
	public static void main(String[] args) {
		
	}

	public List<LinkedHashMap<String,Object>> getQueryMap(String sql){
        List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
        JsonRpcHttpClient client;
        try {
            //实例化请求地址，注意服务端web.xml中地址的配置
            client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8080/json-Rpc"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return list;
        }

        //请求头中添加的信息
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("UserKey", "www.honglonglong.com");
        //添加到请求头中去
        client.setHeaders(headers);
        try {
            //将请求参数封装为数组（注：服务端publicQuery方法的参数个数）
            Object [] send =new Object[3];
            send[0]="我是轰隆隆";
            send[1]="轰隆隆是我";
            send[2]=sql;
            long x1=System.currentTimeMillis();
            //此处就是向服务端获取数据。publicQuery就是ReceiveChannel对外公开的方法。
//            list = client.invokes("publicQuery",send);
            client.invoke("publicQuery", send);
            System.out.println("请求时间："+ (System.currentTimeMillis()-x1)+"毫秒，返回记录数："+list.size()+"条，SQL："+sql);
            return list;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
