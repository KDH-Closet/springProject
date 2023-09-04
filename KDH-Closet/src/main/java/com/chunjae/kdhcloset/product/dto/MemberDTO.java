package com.chunjae.kdhcloset.product.dto;

import com.chunjae.kdhcloset.member.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MemberDTO {

    private Long memberIdx;

    private String name;
    private String gender;
    private String id;
    private String password;
    private Date birth;
    private String tel;
    private int    zipcode;
    private String addr;
    private int     point;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;

//    public static MemberDTO fromMember(Member m) {
//        MemberDTO dto = new MemberDTO();
//        dto.setMemberIdx(m.getMemberIdx());
//        dto.setName(m.getName());
//        dto.setGender(String.valueOf(m.getGender()));
//        dto.setId(m.getId());
//        dto.setBirth(m.getBirth());
//        dto.setTel(m.getTel());
//        dto.setZipcode(m.getZipcode());
//        dto.setAddr(m.getAddr());
//        dto.setPoint(m.getPoint());
//        dto.setRegDate(m.getRegDate());
//        dto.setModifyDate(m.getModifyDate());
//
//        return dto;
//    }
}
