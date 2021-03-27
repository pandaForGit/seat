package web.panda.wechar.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import web.panda.wechar.service.LoginService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class LoginServiceImplTest {
    @Autowired
    private LoginService loginService;
    @Test
    void saveUser() {
        loginService.saveUser("123");
    }
}