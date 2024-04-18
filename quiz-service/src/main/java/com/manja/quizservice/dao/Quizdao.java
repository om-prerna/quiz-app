package com.manja.quizservice.dao;



import com.manja.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Quizdao extends JpaRepository<Quiz, Integer> {
}
