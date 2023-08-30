package com.chunjae.kdhcloset.member.dto;

import com.chunjae.kdhcloset.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

public class MemberDto {

    @Data
    public static class JoinRequestDto {
        @NotBlank(message = "회원 가입 실패\n사유: 이름값이 없습니다.")
        private String name;
        @NotNull(message = "회원 가입 실패\n사유: 성별값이 없습니다.")
        private char gender;
        @NotBlank(message = "회원 가입 실패\n사유: 아이디값이 없습니다.")
        private String id;
        @NotBlank(message = "회원 가입 실패\n사유: 비밀번호값이 없습니다.")
        private String password;
        @NotBlank(message = "회원 가입 실패\n사유: 비밀번호 체크값이 없습니다.")
        private String passwordCheck;
        @NotNull(message = "회원 가입 실패\n사유: 생일값이 없습니다.")
        private Date birth;
        @NotBlank(message = "회원 가입 실패\n사유: 전화번호값이 없습니다.")
        private String tel;
        @NotBlank(message = "회원 가입 실패\n사유: 주소값이 없습니다.")
        private String addr;
        @NotNull(message = "회원 가입 실패\n사유: 우편번호값이 없습니다.")
        private int zipCode;

        public Member toEntity(String encodedPassword) {
            return new Member(this.name, this.gender, this.id, encodedPassword, this.birth, this.tel, this.addr, this.zipCode);
        }

    }
}
