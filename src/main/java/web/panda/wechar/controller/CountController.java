package web.panda.wechar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.panda.common.response.ObjectResponseResult;
import web.panda.utils.RedisUtils;
import web.panda.wechar.service.CountService;

@RestController
@RequestMapping("/wechat/count")
public class CountController {
    @Autowired
    private CountService countService;
    @RequestMapping("/index")
    public ObjectResponseResult getIndexCount(){
        return countService.getIndexCount();
    }
}
