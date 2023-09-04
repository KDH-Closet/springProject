package com.chunjae.kdhcloset.member.dto;

import com.chunjae.kdhcloset.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

public class MemberDto {

    @Data
    public static class JoinRequestDto {
        @NotBlank(message = "회원 가입 실패\n사유: 이름값이 없습니다.")
        @Size(min = 2, message = "이름은 최소 2자 이상이어야 합니다.")
        private String name;

        @NotBlank(message = "회원 가입 실패\n사유: 성별값이 없습니다.")
        private String gender;

        @NotBlank(message = "회원 가입 실패\n사유: 아이디값이 없습니다.")
        @Size(min = 3, max = 10, message = "아이디는 3자 이상 10자 이하이어야 합니다.")
        private String memberId;

        @NotBlank(message = "회원 가입 실패\n사유: 비밀번호값이 없습니다.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,15}$",
                message = "비밀번호는 영어 대소문자, 숫자, 특수문자(@#$%^&+=!)를 모두 포함하는 8~15자여야 합니다."
        )
        private String password;

        @NotBlank(message = "회원 가입 실패\n사유: 비밀번호 체크값이 없습니다.")
        private String passwordCheck;

        @NotBlank(message = "회원 가입 실패\n사유: 생일(연도)값이 없습니다.")
        private String birthYear;

        @NotBlank(message = "회원 가입 실패\n사유: 생일(월)값이 없습니다.")
        private String birthMonth;

        @NotBlank(message = "회원 가입 실패\n사유: 생일(일)값이 없습니다.")
        private String birthDate;

        @NotBlank(message = "회원 가입 실패\n사유: 전화번호1값이 없습니다.")
        @Pattern(regexp = "^[0-9]{3}$", message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String tel1;

        @NotBlank(message = "회원 가입 실패\n사유: 전화번호2값이 없습니다.")
        @Pattern(regexp = "^[0-9]{4}$", message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String tel2;

        @NotBlank(message = "회원 가입 실패\n사유: 전화번호3값이 없습니다.")
        @Pattern(regexp = "^[0-9]{4}$", message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String tel3;

        @NotBlank(message = "회원 가입 실패\n사유: 주소값이 없습니다.")
        private String addr1;

        @NotBlank(message = "회원 가입 실패\n사유: 상세 주소값이 없습니다.")
        private String addr2;

        @NotNull(message = "회원 가입 실패\n사유: 우편번호값이 없습니다.")
        private String zipCode;

        public Member toEntity(String encodedPassword) {
            String birth = this.birthYear + "-" + this.birthMonth + "-" + birthDate;
            String tel = this.tel1 + "-" + this.tel2 + "-" + this.tel3;
            String addr = this.addr1 + " " + this.addr2;
            return new Member(this.name, this.gender, this.memberId, encodedPassword, Date.valueOf(birth), tel, addr, Integer.parseInt(this.zipCode));
        }

    }

    @Data
    public class EditMemberRequestDto {
        private String name;
        private String gender;
        private String tel1;
        private String tel2;
        private String tel3;
        private String addr1;
        private String addr2;
        private String zipCode;
    }
}
