package web.panda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import web.panda.dao.SeatDao;
import web.panda.entity.Seat;
import web.panda.tcp.server.NettyServer;
import web.panda.tcp.server.tcpserver.TcpServer;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class SeatApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeatApplication.class, args);
		TcpServer tcpServer = new TcpServer();
		tcpServer.start();
	}

}
