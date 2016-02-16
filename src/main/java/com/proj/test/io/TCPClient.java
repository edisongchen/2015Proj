/**
 * 
 */
package com.proj.test.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * 
 * @author ctg
 * @date 2016年2月16日
 */
public class TCPClient {
	public static void main(String[] args) {  
        try {  
            Socket socket = new Socket("127.0.0.1", 9090);  
            // 向服务器端发送数据  
            OutputStream os =  socket.getOutputStream();  
            DataOutputStream bos = new DataOutputStream(os);  
            bos.writeBytes("12onnect");  
            bos.flush();  

            // 接收服务器端数据  
            InputStream is = socket.getInputStream();  
            DataInputStream dis = new DataInputStream(is);
            //System.out.println(dis.readUTF());
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
}
