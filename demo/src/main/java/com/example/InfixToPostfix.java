package com.example;


import java.util.*;

public class InfixToPostfix {
    private static int precedence(char c) // to determine precedence of operators
    {
        return switch (c) {
            case '*' -> 3;
            case '.' -> 2;      // implicit concat
            case '|' -> 1;
            default -> 0;
        };
    }

    public static char[] convert(char[] infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix) {
            if (Character.isLetterOrDigit(c))// if the character is an operand (a-z, 0-9)
             {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop(); // remove '('
            } else { // operator
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString().toCharArray(); // return as char array
    }
}