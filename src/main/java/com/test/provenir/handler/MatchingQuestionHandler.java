package com.test.provenir.handler;

import com.test.provenir.contants.ApplicationConstants;
import com.test.provenir.exception.RoboBobServiceException;
import com.test.provenir.model.QuestionAnswer;
import com.test.provenir.repository.QuestionsRepository;
import com.test.provenir.service.SentenceComparisonService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
@Service

public class MatchingQuestionHandler implements QuestionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MatchingQuestionHandler.class);
    @Autowired
    QuestionsRepository predefinedQuestionRepository;
    //SentenceComparisonService sentenceComparisonService;
    public static final String FAILED_TO_LOAD_QUESTIONS_MESSAGE = "Failed to load predefined questions";
    public static final double MIN_MATCHING_CRITERIA_LIMIT = 0.83;

    private List<QuestionAnswer> predefinedQuestions;

    @PostConstruct
    public void init() throws IOException {
        try {
            predefinedQuestions = predefinedQuestionRepository.findAll();
        } catch (IOException e) {
            throw new RoboBobServiceException(FAILED_TO_LOAD_QUESTIONS_MESSAGE, e);
        }
    }

    @Override
    public String answer(String question) throws InvalidPropertiesFormatException {
        double maxSimilarity = 0.0;
        String answer = "";
        for (QuestionAnswer predefinedQuestion : predefinedQuestions) {
            double pySimilarity = SentenceComparisonService.compareAndGetMatchingScore(predefinedQuestion.getQuestion(), question);
            if (pySimilarity > maxSimilarity) {
                maxSimilarity = pySimilarity;
                answer = predefinedQuestion.getAnswer();
            }
            // Here we can make use of two libraries to compare the scores and get proper accuracy.
            // We can introduce two parallel calls using CompletableFuture and combine the responses and process logic to get faster response.
//                double scoreFromJavaLib = TestSimilarityChecker.compareAndGetScore(predefinedQuestion.getQuestion(), question);
//                if (scoreFromJavaLib > javaMaxSimilarity) {
//                    scoreFromJavaLib = javaMaxSimilarity;
//                } else {
//                    bothMatched = false;
//                }
        }
        logger.info("Max Matching score {} for the queried question {}", maxSimilarity, question);
        return maxSimilarity >= MIN_MATCHING_CRITERIA_LIMIT ? answer : getNoMatchingQuestionResponse();
    }

    private String getNoMatchingQuestionResponse() {
        return "Unable to answer for the requested question, please try a different Question";
    }

    @Override
    public String getType() {
        return ApplicationConstants.PREDEFINED;
    }

}
