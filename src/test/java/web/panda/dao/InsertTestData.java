package web.panda.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import web.panda.common.Common;
import web.panda.entity.Seat;
import web.panda.utils.RedisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTestData {
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private RedisUtils redisUtils;
    @Test
    public void addSeat(){
        seatDao.deleteAll();
        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < Common.SEATSIZE; i++) {
            Seat seat = new Seat();
            seat.setNumber((long) i);
            seat.setState(new Random().nextInt(3));
            seatList.add(seat);
        }
        seatDao.saveAll(seatList);
    }
    @Test
    public void insertRedisCache(){
        for (int i = 1; i <= 7; i++) {
            redisUtils.set(i+"",new Random().nextInt(60)+"");
            System.out.println(redisUtils.get(i + ""));
        }
//        redisUtils.set("sumOfUser",);
    }
}
