package web.panda.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import web.panda.common.Common;
import web.panda.entity.Seat;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ChangeStateTest {
    @Autowired
    private SeatDao seatDao;
    @Test
    public void changeState() throws InterruptedException {
        int number = 0;
        while(true){
            Thread.sleep(500);
//            List<Seat> seatList = seatDao.findByNumber(new Random().nextInt(60));
            List<Seat> seatList = seatDao.findByNumber(number);
            Seat seat = seatList.get(0);
            log.info("get number="+seat.getNumber()+"state="+seat.getState());
            seat.setState(new Random().nextInt(3));
            log.info("set number="+seat.getNumber()+"state="+seat.getState());
            seatDao.saveAndFlush(seat);
            number = ++number== Common.SEATSIZE ?0:number;
//            System.out.println(number);
        }
    }
}
