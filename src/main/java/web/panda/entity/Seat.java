package web.panda.entity;

import lombok.Data;
import web.panda.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Seat extends BaseEntity {
    @Column(columnDefinition = "bit(2) default 0")
    private Integer state;
    //true是1 false是0 2是坏了
    private Long number;
    //编号，对应座位表
}
