package web.panda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.panda.entity.WxUser;

@Repository
public interface WxUserDao extends JpaRepository<WxUser, String>//分页和排序
{
    boolean existsByOpenId(String openId);
}
