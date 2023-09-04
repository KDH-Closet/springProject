package com.chunjae.kdhcloset.mypage.repository;

import com.chunjae.kdhcloset.mypage.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN FETCH o.product p JOIN FETCH o.member m " +
            "WHERE m.memberIdx = :memberIdx " +
            "order by o.orderDate DESC ")
    List<Order> findAllWithProductAndMember(@Param("memberIdx") Long memberIdx);

    @Query("SELECT o.orderIdx, o.orderCount, o.orderDate, m.memberIdx, m.addr, m.point, m.name, " +
            "p.name, p.price, i.storedFileName, i.product.productIdx, MIN(i.product.productIdx) " +
            "FROM Order o " +
            "INNER JOIN o.member m " +
            "INNER JOIN o.product p " +
            "INNER JOIN ProductImg i " +
            "On p.productIdx = i.product.productIdx " +
            "WHERE m.memberIdx = :memberIdx " +
            "GROUP BY p.productIdx " +
            "ORDER BY o.orderDate DESC")
    List<Order> findOrderInfoByMemberId(@Param("memberIdx") Long memberIdx);


}
