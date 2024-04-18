package com.manja.questionservice.service;


import com.manja.questionservice.dao.QuestionDao;
import com.manja.questionservice.model.Question;
import com.manja.questionservice.model.QuestionWrapper;
import com.manja.questionservice.model.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuesions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> createQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer noQ) {
           List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, noQ);
           return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds) {
        List<Question> questions = new ArrayList<>();
        for(Integer id: questionsIds){
            questions.add(questionDao.findById(id).get());
        }
        List<QuestionWrapper> wrappers = new ArrayList<>();
        for(Question q : questions){
            QuestionWrapper wrapper = new QuestionWrapper(q.getId(),
                    q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<UserInput> userInput) {
        int score = 0;
        for (UserInput input : userInput){
            Question question = questionDao.findById(Integer.valueOf(input.getQuestionId())).get();
            if(input.getResponse().equals(question.getRightAnswer()))
                score += 1;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
