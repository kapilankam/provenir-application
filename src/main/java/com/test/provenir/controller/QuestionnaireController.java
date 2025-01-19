package com.test.provenir.controller;

import com.test.provenir.model.QuestionResponse;
import com.test.provenir.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequestMapping("/api")
public class QuestionnaireController {
    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping("/answer")
    ResponseEntity<QuestionResponse> getAnswer(@RequestParam String question) throws InvalidPropertiesFormatException {
        QuestionResponse questionResponse = questionnaireService.answer(question);
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }
}
