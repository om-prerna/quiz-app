package com.manja.quizservice.controller;


import com.manja.quizservice.model.QuestionWrapper;
import com.manja.quizservice.model.Quiz;
import com.manja.quizservice.model.UserInput;
import com.manja.quizservice.model.UserOutput;
import com.manja.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam("category") String category,
                                           @RequestParam int numQ,
                                           @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("{quiz}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("quiz") Integer quizId){
        return quizService.getQuizQuesions(quizId);
    }

    @PostMapping("submit/{Id}")
    public ResponseEntity<Integer> calculateQuizResult(@PathVariable("Id") String quizId,
                                                                @RequestBody List<UserInput>  inputList){
        return quizService.calculateQuizResult(inputList);
    }
}
