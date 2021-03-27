package web.panda.tcp.server.tcpserver;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TcpClient {


    public static void main(String[] args) throws IOException {
        String ip = "47.103.195.19";
        String localhost = "localhost";
        //创建客户端Socket，指定服务器地址和端口
        // 1 创建客户端Socket，指定服务器和端口
        Socket socket = new Socket(localhost, 8888);
        //2 获取输出流，向服务端发送信息
        OutputStream os = socket.getOutputStream();//字节输入流
        PrintWriter pw = new PrintWriter(os);//将输出流打包为打印流
        pw.write("account：admin;password:123");
        pw.flush();
        socket.shutdownOutput();//关闭输出流
//3 获取输入流 接受服务端的响应信息
        InputStream is = socket.getInputStream();//字节输入流
        InputStreamReader isr = new InputStreamReader(is);//转换为字符流

//为字符输入流添加缓冲
        BufferedReader br = new BufferedReader(isr);

        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("接受到服务器信息：" + info);

        }
//关闭资源
        br.close();
        is.close();
        isr.close();
        pw.close();
        os.close();
        socket.close();

    }
}
