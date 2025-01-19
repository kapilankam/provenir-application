package com.test.provenir.repository.impl;

import com.test.provenir.model.QuestionAnswer;
import com.test.provenir.repository.QuestionsRepository;
import com.test.provenir.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class QuestionsRepositoryImpl implements QuestionsRepository {

    public static final String FILE_PATH = "classpath:predefined-questions.json";

    @Autowired
    private FileService fileService;

    @Override
    public List<QuestionAnswer> findAll() throws IOException {
        return fileService.loadQuestionAnswers(FILE_PATH);
        // Here, we can integrate with Database for loading questions.
    }
}
