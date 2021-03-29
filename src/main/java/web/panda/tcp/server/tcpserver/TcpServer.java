package web.panda.tcp.server.tcpserver;

import web.panda.utils.ApplicationContextProvider;
import web.panda.wechar.service.SeatService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public void start(){
        try{
            //创建一个服务器端的Socket，即ServerSocket，绑定需要监听的端口
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            //记录连接过服务器端的数量
            int count=0;
            System.out.println("***server is waiting connect***");
            while(true){//循环侦听新的客户端的连接
                //调用accept()方法侦听，等待客户端的连接以获取Socket实例
                socket = serverSocket.accept();
                //创建新线程
                Thread thread = new Thread(new ServerThread(socket, ApplicationContextProvider.getBean(SeatService.class)));
                thread.start();
                count++;
                InetAddress address = socket.getInetAddress();
                System.out.println(address.getHostAddress()+"is connected");
//                System.out.println("client IP："+address.getHostAddress());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
