/**
 * 
 */
package com.proj.test.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author ctg
 * @date 2016年2月16日
 */
public class TCPServer {
    public static void main(String[] args) {  
        try {  
            ServerSocket serverSocket = new ServerSocket(9090); 
            while(true){
            	
            Socket socket = serverSocket.accept();  
            // 读取客户端数据  
            InputStream info = socket.getInputStream();  
            DataInputStream dis = new DataInputStream(info);  
            System.out.println(dis.readUTF());  

            // 向客户端输出数据  
            OutputStream os = socket.getOutputStream();  
            DataOutputStream dos = new DataOutputStream(os);  
            dos.writeUTF("Hello!");  
            dos.flush();  
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }  
    }
}
