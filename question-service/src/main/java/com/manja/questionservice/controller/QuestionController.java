package com.manja.questionservice.controller;


import com.manja.questionservice.model.Question;
import com.manja.questionservice.model.QuestionWrapper;
import com.manja.questionservice.model.UserInput;
import com.manja.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuesions();

    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Question>> getAllquestionsbyCategory(@PathVariable("cat") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/myQuestion")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
            return questionService.createQuestion(question);
    }


    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
                                        @RequestParam String category, @RequestParam Integer noQ){
        return questionService.getQuestionsForQuiz(category, noQ);
    }

    @PostMapping("get-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionsIds);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<UserInput> userInput){
        return questionService.calculateScore(userInput);
    }
}

