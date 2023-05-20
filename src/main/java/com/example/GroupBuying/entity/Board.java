package com.example.GroupBuying.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String recruitment;
    private String item;
    private String content;
    private String category;
    @Column(name = "writer_id")
    private String writer;
    private LocalDateTime datetime;
    private LocalDate date;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "member_number")
    private MemberEntity member;

    public void setMember(MemberEntity member){
        this.member=member;
        member.getBoardList().add(this);
    }
}
