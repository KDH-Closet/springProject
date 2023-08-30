package com.chunjae.kdhcloset.member.service;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.dto.MemberDto;
import com.chunjae.kdhcloset.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Provider;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    /*
    * 회원 가입
    * 1. 비밀번호 체크 일치 확인
    * 2. 아이디 중복 확인
    * 3. 전화번호 중복 확인
    * */
    public ResponseEntity<String> join(MemberDto.JoinRequestDto requestDto) {

        ResponseEntity<String> BAD_REQUEST = getStringResponseEntity(requestDto);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        Member member = requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword()));
        memberRepository.save(member);
        return new ResponseEntity<>("회원 가입 성공.", null, HttpStatus.OK);
    }

    /*
     * 회원 가입 - 모델 버전 테스트
     * 1. 비밀번호 체크 일치 확인
     * 2. 아이디 중복 확인
     * 3. 전화번호 중복 확인
     * */
    /*public void join(MemberDto.JoinRequestDto requestDto) {
        Member member = requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword()));
        memberRepository.save(member);
    }*/

    private ResponseEntity<String> getStringResponseEntity(MemberDto.JoinRequestDto requestDto) {
        if (memberRepository.findById(requestDto.getId()).isPresent()) {
            return new ResponseEntity<>("회원 가입 실패\n사유: 종복된 아이디입니다.", null, HttpStatus.BAD_REQUEST);
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())) {
            return new ResponseEntity<>("회원 가입 실패\n사유: 비밀번호 체크가 일치하지 않습니다.", null, HttpStatus.BAD_REQUEST);
        }

        if (memberRepository.findByTel(requestDto.getTel()).isPresent()) {
            return new ResponseEntity<>("회원 가입 실패\n사유: 중복된 전화번호입니다.", null, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
