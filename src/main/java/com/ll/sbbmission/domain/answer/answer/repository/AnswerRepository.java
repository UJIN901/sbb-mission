package com.ll.sbbmission.domain.answer.answer.repository;

import com.ll.sbbmission.domain.answer.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
}
