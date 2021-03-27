package web.panda.wechar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.panda.dao.LoginLogDao;
import web.panda.dao.WxUserDao;
import web.panda.entity.LoginLog;
import web.panda.entity.WxUser;
import web.panda.utils.DateUtil;
import web.panda.utils.RedisUtils;
import web.panda.wechar.service.LoginService;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private LoginLogDao loginLogDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void saveUser(String openId) {
        if (!wxUserDao.existsByOpenId(openId)) {
            wxUserDao.save(new WxUser(openId));
        }
//        LoginLog loginLog = new LoginLog(one, new Date());
//        loginLogDao.save(loginLog);
        String day = DateUtil.getDay() + "";
        int count = Integer.parseInt(redisUtils.get(day).toString()) + 1;
        redisUtils.set(day, count + "");
    }
}
