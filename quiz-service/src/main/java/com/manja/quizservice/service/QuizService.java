package com.manja.quizservice.service;


import com.manja.quizservice.dao.Quizdao;
import com.manja.quizservice.feign.QuizInterface;
import com.manja.quizservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

   @Autowired
   Quizdao quizdao;

   @Autowired
   QuizInterface quizInterface;

   public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {

        List<Integer> questionsId =  quizInterface.getQuestionsForQuiz(category, numQ).getBody();

         Quiz quiz = new Quiz();
         quiz.setQuestionsId(questionsId);
         quiz.setTitle(title);
         quizdao.save(quiz);

      return new ResponseEntity<>(quiz, HttpStatus.CREATED);
   }

   public ResponseEntity<List<QuestionWrapper>> getQuizQuesions(Integer quizId) {
       List<Integer> questionsId = quizdao.findById(quizId).get().getQuestionsId();
       List<QuestionWrapper> questionsForUser = new ArrayList<>();

       questionsForUser = quizInterface.getQuestionsFromId(questionsId).getBody();
       return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
   }

   public ResponseEntity<Integer> calculateQuizResult(List<UserInput> inputList) {

       ResponseEntity<Integer> score = quizInterface.calculateScore(inputList);
      return new ResponseEntity<>(score, HttpStatus.OK);
   }
}

