package web.panda.randomThread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import web.panda.common.Common;
import web.panda.dao.SeatDao;
import web.panda.entity.Seat;
import web.panda.utils.DateUtil;
import web.panda.utils.RedisUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class RandomMachine {
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private RedisUtils redisUtils;
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteRedisKey(){
        redisUtils.set(DateUtil.getDay()+"","0");
    }
    @Scheduled(cron = "0/1 * * * * *")
    public void startMachine() {
//        log.info("startMachine启动");
        int number = 0;
//            List<Seat> seatList = seatDao.findByNumber(new Random().nextInt(60));
        for (int i = 0; i < 30; i++) {
            List<Seat> seatList = seatDao.findByNumber(number);
            Seat seat = seatList.get(0);
//        log.info("get number=" + seat.getNumber() + "state=" + seat.getState());
            seat.setState(new Random().nextInt(3));
//            log.info("set number=" + seat.getNumber() + "state=" + seat.getState());
            seatDao.saveAndFlush(seat);
            number = ++number == Common.SEATSIZE ? 0 : number;
//            System.out.println(number);
        }
    }
}
