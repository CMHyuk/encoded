package com.password.encoded.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String password;

    @Builder
    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
