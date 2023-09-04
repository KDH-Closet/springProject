package com.chunjae.kdhcloset.mypage.repository;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.mypage.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
        List<Cart> findByMember(Member member);

        void deleteByCartIdx(Long cartIdx);
}
