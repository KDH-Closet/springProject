package com.chunjae.kdhcloset.member.repository;

import com.chunjae.kdhcloset.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);

    Optional<Member> findByTel(String tel);

    void deleteById(String id);
}
