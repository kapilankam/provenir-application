package com.test.provenir.repository;

import com.test.provenir.model.QuestionAnswer;

import java.io.IOException;
import java.util.List;

public interface QuestionsRepository {
    List<QuestionAnswer> findAll() throws IOException;
}
