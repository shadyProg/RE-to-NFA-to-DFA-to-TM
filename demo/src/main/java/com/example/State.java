package com.example;

import java.util.*;

public class State {
    public Map<Character, List<State>> transitions = new HashMap<>(); //list of states for each character transition
    public List<State> epsilonTransitions = new ArrayList<>();// list of states for epsilon transitions
       private static int counter = 0; // For unique state IDs
    public final int id;
    public State() {
        this.id = counter++;
    }

    @Override
    public String toString() {
        return "q" + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return id == state.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


