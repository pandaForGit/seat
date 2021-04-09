package web.panda.tcp.server.tcpserver;

import lombok.extern.slf4j.Slf4j;
import web.panda.wechar.service.SeatService;

import java.io.*;
import java.net.Socket;
import java.util.Date;

@Slf4j
public class ServerThread implements Runnable {
    Socket socket = null;//和本线程相关的Socket
    SeatService seatService;

    public ServerThread(Socket socket, SeatService seatService) {
        this.socket = socket;
        this.seatService = seatService;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            // 获取输入流，并读取客户端信息
            is = socket.getInputStream();
//            isr = new InputStreamReader(is);
//            br = new BufferedReader(isr);
            byte[] buf = new byte[8];
            int len;
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            String[] binaryStringArray = new String[8];
            while ((len = is.read(buf)) != -1) {// len值为-1时，表示没有数据了
                // append方法往sb对象里面添加数据
                socket.shutdownInput();//关闭输入流
//                System.out.println(Arrays.toString(buf));
                for (int i = 0; i < len; i++) {
                    binaryStringArray[i] = Integer.toBinaryString((buf[i] & 0xFF) + 0x100).substring(1);
                    System.out.println("data[" + i + "]" + binaryStringArray[i]);
//                    pw.write("data[" + i + "]" + binaryStringArray[i]);
                }
                String info = new String(buf, 0, 8);
                pw.write(info);
//                System.out.println(new Date()+socket.getInetAddress().getAddress().toString()+":"+info);
                pw.flush();//输出缓冲区
                if (buf[4] == 1) {
                    int seatNumber = buf[3];
                    int state = 1;
                    System.out.println(buf[5]);
                    if (buf[5] == 1) {
                        state = 0;
                    }
                    seatService.changState(seatNumber, state);
                    System.out.println("SeatStateChange  Event"+new String(buf));
                    log.info("将"+seatNumber+"状态改为:"+state);
//                    pw.write("将"+seatNumber+"状态改为:"+state);
                }
                if(buf[4]==2){
                    System.out.println("HeartBeat Event"+new String(buf));
                }
                break;
            }
            // 获取输出流，响应客户端的请求
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源即相关socket
            try {
                if (pw != null)
                    pw.close();
                if (os != null)
                    os.close();
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
