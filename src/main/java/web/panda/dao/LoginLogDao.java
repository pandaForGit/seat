package web.panda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.panda.entity.LoginLog;

import java.util.Date;

@Repository
public interface LoginLogDao extends
        JpaRepository<LoginLog, String>//分页和排序
{
//    int countByDate(Date date);
}