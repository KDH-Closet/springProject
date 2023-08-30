package com.chunjae.kdhcloset.member.service;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));
        return User.builder().username(member.getId()).password(member.getPassword()).build();
    }
}
