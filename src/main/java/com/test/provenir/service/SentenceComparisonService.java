package com.test.provenir.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
@Service
public class SentenceComparisonService {
    private static final Logger logger = LoggerFactory.getLogger(SentenceComparisonService.class);
    public static final String COMPARE_SCRIPT_LOCATION = "scripts/sentence_similarity.py";
    public static final String PYTHON_COMMAND = "python3";


    public static void main(String[] args) throws InvalidPropertiesFormatException {
        double val = compareAndGetMatchingScore("What is your age?", "How Old are you?");
        System.out.println(val);
    }

    public static Double compareAndGetMatchingScore(String sentence1, String sentence2) throws InvalidPropertiesFormatException {
        return Double.valueOf(runComparisonScript(sentence1, sentence2));
    }

    private static String runComparisonScript(String sentence1, String sentence2) throws InvalidPropertiesFormatException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        try {
            CommandLine commandLine = new CommandLine(PYTHON_COMMAND);
            commandLine.addArgument(COMPARE_SCRIPT_LOCATION);
            commandLine.addArguments(new String[]{sentence1, sentence2});

            DefaultExecutor executor = new DefaultExecutor();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            executor.setStreamHandler(streamHandler);

            int exitCode = executor.execute(commandLine);

            if (exitCode == 0) {
                return outputStream.toString();
            } else {
                throw new InvalidPropertiesFormatException("Comparison failed");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new InvalidPropertiesFormatException("Comparison failed");
        }
    }
}
