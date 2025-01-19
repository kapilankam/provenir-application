package com.test.provenir.util;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class TestSimilarityChecker {
    public static void main(String[] args) {
        String sentence1 = "What is your age?";
        String sentence2 = "How Old are you?";

        compareAndGetScore(sentence1, sentence2);
    }

    public static double compareAndGetScore(String sentence1, String sentence2) {
        // Initialize Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Process sentences
        Map<String, Integer> vector1 = processSentence(sentence1, pipeline);
        Map<String, Integer> vector2 = processSentence(sentence2, pipeline);

        // Calculate cosine similarity
        double similarityScore = cosineSimilarity(vector1, vector2);
        System.out.println("Cosine Similarity Score: " + similarityScore);
        return similarityScore;
    }

    private static Map<String, Integer> processSentence(String sentence, StanfordCoreNLP pipeline) {
        Map<String, Integer> wordFrequencies = new HashMap<>();

        // Annotate the sentence
        Annotation document = new Annotation(sentence);
        pipeline.annotate(document);

        // Process tokens
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap coreMap : sentences) {
            coreMap.get(CoreAnnotations.TokensAnnotation.class).forEach(token -> {
                // Get lemmatized form of the word
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class).toLowerCase();
                wordFrequencies.put(lemma, wordFrequencies.getOrDefault(lemma, 0) + 1);
            });
        }

        return wordFrequencies;
    }

    private static double cosineSimilarity(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        Set<String> allWords = new HashSet<>(vector1.keySet());
        allWords.addAll(vector2.keySet());

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for (String word : allWords) {
            int count1 = vector1.getOrDefault(word, 0);
            int count2 = vector2.getOrDefault(word, 0);

            dotProduct += count1 * count2;
            magnitude1 += count1 * count1;
            magnitude2 += count2 * count2;
        }

        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
    }
}
