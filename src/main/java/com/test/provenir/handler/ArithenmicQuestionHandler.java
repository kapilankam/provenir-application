package com.test.provenir.handler;

import com.test.provenir.contants.ApplicationConstants;
import com.test.provenir.util.ArithmeticExpressionCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
//@Slf4j
public class ArithenmicQuestionHandler implements QuestionHandler {
    @Override
    public String answer(String question) {
        double result = ArithmeticExpressionCheckUtil.calculate(question);
        //log.debug("Calculation result {} of the arithmetic question {}", result, question);
        return String.valueOf(result);
    }

    @Override
    public String getType() {
        return ApplicationConstants.ARITHMETIC;
    }
}
