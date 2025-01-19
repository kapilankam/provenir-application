package com.test.provenir.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.provenir.model.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private ResourceLoader resourceLoader;

    public List<QuestionAnswer> loadQuestionAnswers(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource(fileName);
        InputStream inputStream = resource.getInputStream();
        List<QuestionAnswer> result = objectMapper.readValue(inputStream, new TypeReference<List<QuestionAnswer>>() {
        });

        return result;
    }
}
