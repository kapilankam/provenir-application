package com.test.provenir.service.impl;

import com.test.provenir.contants.ApplicationConstants;
import com.test.provenir.factory.QuestionHandlerFactory;
import com.test.provenir.handler.QuestionHandler;
import com.test.provenir.model.QuestionResponse;
import com.test.provenir.service.QuestionnaireService;
import com.test.provenir.util.ArithmeticExpressionCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {


    @Autowired
    QuestionHandlerFactory questionHandlerFactory;

    @Override
    public QuestionResponse answer(String question) throws InvalidPropertiesFormatException {
        boolean isValidExpression = ArithmeticExpressionCheckUtil.isValidExpression(question);
        String handlerType = isValidExpression ? ApplicationConstants.ARITHMETIC : ApplicationConstants.PREDEFINED;
        QuestionHandler handler = questionHandlerFactory.getService(handlerType);
        String answer = handler.answer(question);
        //return QuestionResponse.builder().answer(answer).type(handlerType).build();
        // Here, we can initiate the history of the messages specific to a user to mysql database.
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setAnswer(answer);
        questionResponse.setType(handlerType);
        return questionResponse;
    }
}
