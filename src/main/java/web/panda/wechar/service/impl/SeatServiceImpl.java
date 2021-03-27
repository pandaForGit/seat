package web.panda.wechar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.panda.common.response.CommonCode;
import web.panda.common.response.ObjectResponseResult;
import web.panda.common.response.ObjectResult;
import web.panda.dao.SeatDao;
import web.panda.entity.Seat;
import web.panda.wechar.service.SeatService;

import java.util.LinkedList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;
    @Override
    public ObjectResponseResult getState() {
        List<Seat> seatList = seatDao.findAllByOrderByNumber();
        List<Integer> stateResultList = new LinkedList<>();
        for (Seat seat : seatList) {
            stateResultList.add(seat.getState());
        }
        ObjectResult<List<Integer>> objectResult = new ObjectResult<>();
        objectResult.setObj(stateResultList);
        ObjectResponseResult objectResponseResult = new ObjectResponseResult(CommonCode.SUCCESS,objectResult);
        return objectResponseResult;
    }
}
