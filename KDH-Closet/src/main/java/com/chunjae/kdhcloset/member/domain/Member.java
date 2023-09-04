package com.chunjae.kdhcloset.member.domain;

import com.chunjae.kdhcloset.util.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tbl_member")
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx", updatable = false, nullable = false)
    private Long memberIdx;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth", nullable = false)
    private Date birth;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "addr", nullable = false)
    private String addr;

    @Column(name = "zip_code", nullable = false)
    private int zipCode;

    @Column(name = "point", nullable = false)
    private int point;

    public Member(String name, String gender, String id, String password, Date birth, String tel, String addr, int zipCode) {
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.password = password;
        this.birth = birth;
        this.tel = tel;
        this.addr = addr;
        this.zipCode = zipCode;
        this.point = 0;
    }
}
