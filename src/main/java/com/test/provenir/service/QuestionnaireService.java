package com.test.provenir.service;

import com.test.provenir.model.QuestionResponse;

import java.util.InvalidPropertiesFormatException;

public interface QuestionnaireService {
    QuestionResponse answer(String question) throws InvalidPropertiesFormatException;
}
