package web.panda.wechar.service;

import web.panda.common.response.ObjectResponseResult;

public interface SeatService {
     ObjectResponseResult getState();
     void changState(int number,int state);
}
