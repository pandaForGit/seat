package web.panda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.panda.entity.Seat;

import java.util.List;

@Repository
public interface SeatDao extends
        JpaRepository<Seat, String>//分页和排序
{
    List<Seat> findAllByOrderByNumber();
    List<Seat> findByNumber(long number);
}