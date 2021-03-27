package web.panda.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class WxUserDaoTest {
    @Autowired
    private WxUserDao wxUserDao;
    @Test
    void existsByOpenId() {
        System.out.println(wxUserDao.existsByOpenId("123"));
    }
}