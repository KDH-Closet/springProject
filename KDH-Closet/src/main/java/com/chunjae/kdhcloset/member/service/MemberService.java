package com.chunjae.kdhcloset.member.service;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.dto.MemberDto;
import com.chunjae.kdhcloset.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public void join(MemberDto.JoinRequestDto requestDto) {

        ResponseEntity<String> BAD_REQUEST = getStringResponseEntity(requestDto);
        if (BAD_REQUEST != null) return;

        Member member = requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword()));
        memberRepository.save(member);
    }

    private ResponseEntity<String> getStringResponseEntity(MemberDto.JoinRequestDto requestDto) {
        if (memberRepository.findById(requestDto.getMemberId()).isPresent()) {
            return new ResponseEntity<>("회원 가입 실패\n사유: 종복된 아이디입니다.", null, HttpStatus.BAD_REQUEST);
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())) {
            return new ResponseEntity<>("회원 가입 실패\n사유: 비밀번호 체크가 일치하지 않습니다.", null, HttpStatus.BAD_REQUEST);
        }

        String tel = requestDto.getTel1() + "-" + requestDto.getTel2() + "-" + requestDto.getTel3();
        if (memberRepository.findByTel(tel).isPresent()) {
            return new ResponseEntity<>("회원 가입 실패\n사유: 중복된 전화번호입니다.", null, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public void editMemberInfoForm(Model model, String memberId) throws UsernameNotFoundException{
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저를 찾을 수 없습니다.")
        );

        model.addAttribute("name", member.getName());
        model.addAttribute("point", member.getPoint());
        model.addAttribute("gender", member.getGender());
        model.addAttribute("memberIdx", member.getMemberIdx());
        model.addAttribute("tel1", member.getTel().split("-")[0]);
        model.addAttribute("tel2", member.getTel().split("-")[1]);
        model.addAttribute("tel3", member.getTel().split("-")[2]);
        model.addAttribute("addr1", String.valueOf(member.getAddr()).split(" ")[0]);
        model.addAttribute("addr2", String.valueOf(member.getAddr()).split(" ")[1]);
        model.addAttribute("zipCode", String.valueOf(member.getZipCode()));
    }

    public void editMemberInfo(MemberDto.EditMemberRequestDto requestDto, String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저를 찾을 수 없습니다.")
        );

        member.setName(requestDto.getName());
        member.setTel(requestDto.getTel1() + "-" + requestDto.getTel2() + "-" + requestDto.getTel3());
        member.setAddr(requestDto.getAddr1() + " " + requestDto.getAddr2());
        member.setZipCode(Integer.parseInt(requestDto.getZipCode()));
        memberRepository.save(member);
    }

    @Transactional
    public void quitMember(String memberId) {
        memberRepository.deleteById(memberId);
    }
}
