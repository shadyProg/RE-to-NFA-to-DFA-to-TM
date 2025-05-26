package com.example;

import java.util.*;

public class State {
    public Map<Character, List<State>> transitions = new HashMap<>(); //list of states for each character transition
    public List<State> epsilonTransitions = new ArrayList<>();// list of states for epsilon transitions
}


