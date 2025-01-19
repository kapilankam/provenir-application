package com.test.provenir.handler;

import java.util.InvalidPropertiesFormatException;

public interface QuestionHandler {
    String answer(String question) throws InvalidPropertiesFormatException;

    String getType();
}
