package com.ll.sbbmission.domain.answer.answer.controller;

import com.ll.sbbmission.domain.answer.answer.AnswerForm;
import com.ll.sbbmission.domain.answer.answer.service.AnswerService;
import com.ll.sbbmission.domain.question.question.entity.Question;
import com.ll.sbbmission.domain.question.question.service.QuestionService;
import com.ll.sbbmission.domain.user.user.entity.SiteUser;
import com.ll.sbbmission.domain.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            model.addAttribute("answerForm", answerForm);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
}
