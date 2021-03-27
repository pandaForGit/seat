package web.panda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 服务号关注者
 */
@Entity
@Data
@NoArgsConstructor
public class WxUser {
    @Id
    private String openId;//用户在服务号的openid
    private String nickname;//用户的微信名
    private String headImgUrl;//用户头像地址
    private int subscribe;//是否关注服务号 1已关注 0是未关注
    private int sexDesc;//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String city;//用户所在城市
    private String country;//用户所在国家
    private String province;//用户所在省份
    private String unionId;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
    private String token;//token用户在登录时有
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp tokenFailTime;//token的失效时间

    public WxUser(String openId) {
        this.openId = openId;
    }
}
