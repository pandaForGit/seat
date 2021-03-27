package web.panda.entity.vo;

import lombok.Data;

@Data
public class CountVo {
    private String[] dailyCount;
    private String sumOfUser;
    private String sumOfWeek;
}
