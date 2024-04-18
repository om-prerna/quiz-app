package com.manja.quizservice.feign;

import com.manja.quizservice.model.QuestionWrapper;
import com.manja.quizservice.model.UserInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String category, @RequestParam Integer noQ);


    @PostMapping("questions/get-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIds);


    @PostMapping("questions/submit")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<UserInput> userInput);
}
