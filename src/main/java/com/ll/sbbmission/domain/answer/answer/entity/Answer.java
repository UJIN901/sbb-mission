package com.ll.sbbmission.domain.answer.answer.entity;

import com.ll.sbbmission.domain.question.question.entity.Question;
import com.ll.sbbmission.domain.user.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

}
