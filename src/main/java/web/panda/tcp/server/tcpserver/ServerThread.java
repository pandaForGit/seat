package web.panda.tcp.server.tcpserver;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Date;

@Slf4j
public class ServerThread implements Runnable{

    Socket socket = null;//和本线程相关的Socket
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            // 获取输入流，并读取客户端信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            //读取数据
            String info = null;
            while((info = br.readLine())!=null){
//                log.info(socket.getInetAddress()+":"+info);
                System.out.println(new Date()+socket.getInetAddress().getAddress().toString()+":"+info);
                socket.shutdownInput();//关闭输入流
//                pw.write("hello ,I got your message:"+info);
                pw.write(info);
                if (socket.isClosed()) {
                    log.warn("socket closed!!!!!");
                }
            }
            // 获取输出流，响应客户端的请求
            pw.flush();//输出缓冲区
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源即相关socket
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
