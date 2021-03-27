package web.panda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.panda.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * 用户登录表
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "wxuser_id")
    private WxUser wxUser;
    private Date date;
}
