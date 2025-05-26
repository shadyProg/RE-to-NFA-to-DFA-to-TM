package com.example;

import java.util.*;

public class NFAConstructor {
    public static NFA buildFromPostfix(char[] postfix) { //Thompson’s Construction،
        Stack<NFA> stack = new Stack<>();

        for (char c : postfix) {
            if (Character.isLetterOrDigit(c)) {
                State start = new State();
                State accept = new State();
                start.transitions.putIfAbsent(c, new ArrayList<>());// Initialize the list if it doesn't exist
                start.transitions.get(c).add(accept);
                stack.push(new NFA(start, accept));
            } else if (c == '*') {
                NFA nfa = stack.pop();
                State start = new State();
                State accept = new State();
                start.epsilonTransitions.add(nfa.start);
                start.epsilonTransitions.add(accept);
                nfa.accept.epsilonTransitions.add(nfa.start);
                nfa.accept.epsilonTransitions.add(accept);
                stack.push(new NFA(start, accept));
            } else if (c == '.') {
                NFA b = stack.pop();
                NFA a = stack.pop();
                a.accept.epsilonTransitions.add(b.start);
                stack.push(new NFA(a.start, b.accept));
            } else if (c == '|') {
                NFA b = stack.pop();
                NFA a = stack.pop();
                State start = new State();
                State accept = new State();
                start.epsilonTransitions.add(a.start);
                start.epsilonTransitions.add(b.start);
                a.accept.epsilonTransitions.add(accept);
                b.accept.epsilonTransitions.add(accept);
                stack.push(new NFA(start, accept));
            }
        }

        return stack.pop();
    }
}