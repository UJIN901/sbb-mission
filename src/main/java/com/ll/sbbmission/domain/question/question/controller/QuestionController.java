package com.ll.sbbmission.domain.question.question.controller;

import com.ll.sbbmission.domain.answer.answer.AnswerForm;
import com.ll.sbbmission.domain.question.question.QuestionForm;
import com.ll.sbbmission.domain.question.question.entity.Question;
import com.ll.sbbmission.domain.question.question.service.QuestionService;
import com.ll.sbbmission.domain.user.user.entity.SiteUser;
import com.ll.sbbmission.domain.user.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("answerForm", answerForm);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(Model model, QuestionForm questionForm) {
        model.addAttribute("questionForm", questionForm);
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }
}
