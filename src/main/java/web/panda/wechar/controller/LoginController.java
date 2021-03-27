package web.panda.wechar.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.panda.utils.RedisUtils;
import web.panda.wechar.service.LoginService;


@RestController
@RequestMapping("/wechat/user")
@Slf4j
public class LoginController {
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LoginService loginService;
    @PostMapping("/login/{code}")
    public Object loginByWeixin(@PathVariable String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            loginService.saveUser(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return JSONObject.toJSONString(session);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return e.toString();
        }
    }
    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JSONObject.toJSONString(userInfo);
    }
}
