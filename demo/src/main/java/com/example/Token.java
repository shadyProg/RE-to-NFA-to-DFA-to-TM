package com.example;

public class Token {
    private char[] tokens;

    public Token(String input) {
        if (!input.matches("[a-z0-9|()* .]+")) {
            throw new IllegalArgumentException("Invalid characters in input. Only a-z, 0-9, |, *, (, ), . and space allowed");
        }

        this.tokens = input.toCharArray();
    }

    public char[] getTokens() {
        return tokens;
    }
}

