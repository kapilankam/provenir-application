package com.test.provenir.util;

import java.util.Stack;
import java.util.regex.Pattern;

public class ArithmeticExpressionCheckUtil {
    static String regex = "^\\s*[-+]?\\d+(\\.\\d+)?(\\s*[-+*/]\\s*\\d+(\\.\\d+)?)*\\s*$";

    // Compile the regex
    static Pattern pattern = Pattern.compile(regex);


    public static boolean isValidExpression(String question) {
        return pattern.matcher(question).matches();
    }

    public static double calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (i + 1 == s.length() || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push((stack.pop() / num));
                }
                sign = c;
                num = 0;
            }
        }
        // O(n) as we iterate the stack to sum
        double result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

}
