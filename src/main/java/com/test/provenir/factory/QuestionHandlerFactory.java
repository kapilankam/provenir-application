package com.test.provenir.factory;

import com.test.provenir.handler.QuestionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionHandlerFactory {
    private static final Map<String, QuestionHandler> myServiceCache = new HashMap<>();

    @Autowired
    private QuestionHandlerFactory(List<QuestionHandler> handlers) {
        for (QuestionHandler handler : handlers) {
            myServiceCache.put(handler.getType(), handler);
        }
    }

    public QuestionHandler getService(String type) {
        QuestionHandler handler = myServiceCache.get(type);
        if (handler == null) {
            throw new RuntimeException("Unknown handler type: " + type);
        }
        return handler;
    }
}