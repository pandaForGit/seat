package web.panda.wechar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.panda.common.response.CommonCode;
import web.panda.common.response.ObjectResponseResult;
import web.panda.common.response.ObjectResult;
import web.panda.dao.WxUserDao;
import web.panda.entity.vo.CountVo;
import web.panda.utils.RedisUtils;
import web.panda.wechar.service.CountService;

@Service
public class CountServiceImpl implements CountService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private WxUserDao wxUserDao;
    @Override
    public ObjectResponseResult getIndexCount() {
        String[] dailyCountList = new String[7];
        int sumOfWeek = 0;
        for (int day = 1; day <= 7; day++) {
            String dailyCount = redisUtils.get(day + "").toString();
            dailyCountList[day - 1] = dailyCount;
            sumOfWeek += Integer.parseInt(dailyCount);
        }
        CountVo countVo = new CountVo();
        countVo.setDailyCount(dailyCountList);
        countVo.setSumOfUser(wxUserDao.count() + "");
        countVo.setSumOfWeek(sumOfWeek+"");
        ObjectResult<CountVo> countVoObjectResult = new ObjectResult<>();
        countVoObjectResult.setObj(countVo);
        ObjectResponseResult objectResponseResult = new ObjectResponseResult(CommonCode.SUCCESS,countVoObjectResult);
        return objectResponseResult;
    }
}
