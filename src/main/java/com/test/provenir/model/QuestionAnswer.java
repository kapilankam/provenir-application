package com.test.provenir.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Valid
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class QuestionAnswer {
    @NotBlank(message = "Question can not be null or blank")
    String question;
    @NotBlank(message = "Answer can not be null or blank")
    String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
