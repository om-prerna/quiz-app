package com.manja.quizservice.model;

import lombok.Data;

@Data
public class UserOutput {

    String questionId;
    String rightAnswer;
    String response;
}
