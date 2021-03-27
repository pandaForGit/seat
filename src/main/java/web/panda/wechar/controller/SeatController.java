package web.panda.wechar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.panda.common.response.ObjectResponseResult;
import web.panda.wechar.service.SeatService;

@RestController
@RequestMapping("/wechat/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @GetMapping("/state")
    public ObjectResponseResult getState(){
        return seatService.getState();
    }
}
