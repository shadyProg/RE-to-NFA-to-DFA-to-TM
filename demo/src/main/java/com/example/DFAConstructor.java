package com.example;
import java.util.*;


public class DFAConstructor {
    private Map<Set<State>, String> stateNames = new HashMap<>();
    private Queue<Set<State>> queue = new LinkedList<>();// Queue to manage the states during the DFA construction can input states and transitions
    private Map<String, Map<Character, String>> dfaTransitions = new HashMap<>();
   
    private Set<String> finalStates = new HashSet<>();
    private int nameCounter = 0;
    NFA nfa;
    public DFAConstructor(NFA nfa) {
        if (nfa == null || nfa.start == null || nfa.accept == null) {
            throw new IllegalArgumentException("NFA must have a valid start and accept state.");
        }
        this.nfa = nfa;
        buildDFA();
    }

     public Map<String, Map<Character, String>> getDFATransitions() {
        return dfaTransitions;
    }

    public Set<String> getFinalStates() {
        return finalStates;
    }

    public String getStartState() {
        return "S0";
    }
    
    private static Set<State> epsilonClosure(Set<State> states) {// Computes the epsilon closure for a set of NFA states
        Stack<State> stack = new Stack<>();
        Set<State> result = new HashSet<>(states);
        stack.addAll(states);

        while (!stack.isEmpty()) {
            State current = stack.pop();
            for (State next : current.epsilonTransitions) {
                if (result.add(next)) {
                    stack.push(next);
                }
            }
        }
        return result;
    }

    public void buildDFA() {// Subset construction algorithm
        Set<State> startClosure = epsilonClosure(Set.of(nfa.start));
        queue.add(startClosure);
        stateNames.put(startClosure, "S" + nameCounter++);

        while (!queue.isEmpty()) {
            Set<State> current = queue.poll();
            String currentstate = stateNames.get(current);
            dfaTransitions.putIfAbsent(currentstate, new HashMap<>());
            // Collect all possible transitions for this subset of NFA states
            Map<Character, Set<State>> transitions = new HashMap<>();
            for (State state : current) {
                for (Map.Entry<Character, List<State>> entry : state.transitions.entrySet()) {
                    transitions.putIfAbsent(entry.getKey(), new HashSet<>());
                    transitions.get(entry.getKey()).addAll(entry.getValue());
                }
            }
            // For each symbol, compute the epsilon closure of the destination set and create new DFA states as needed
            for (Map.Entry<Character, Set<State>> entry : transitions.entrySet()) {
                Set<State> closure = epsilonClosure(entry.getValue());
                stateNames.putIfAbsent(closure, "S" + nameCounter);
                if (!dfaTransitions.containsKey(stateNames.get(closure))) {
                    queue.add(closure);
                    nameCounter++;
                }
                // Add a DFA to transition from the current DFA state and on this symbol go to the DFA state apper the closure set
                dfaTransitions.get(currentstate).put(entry.getKey(), stateNames.get(closure));
            }
            // Mark DFA state as final if any NFA accept state is present in the subset
            if (current.contains(nfa.accept)) {
                finalStates.add(currentstate);
            }
        }
        
    }

   public void print() {
    System.out.println("\n--- DFA Transitions ---");
    for (var entry : dfaTransitions.entrySet()) {
        String state = entry.getKey();
        System.out.println("State " + state + ":");
        for (var trans : entry.getValue().entrySet()) {
            System.out.println("  " + trans.getKey() + " -> " + trans.getValue());
        }
    }
    System.out.println("\nFinal States: " + finalStates);
}
    
   public void printAllTransitions() {
  
    Set<Character> alphabet = new HashSet<>();
    for (Map<Character, String> trans : dfaTransitions.values()) {
        alphabet.addAll(trans.keySet());
    }

    System.out.println("\n--- DFA AllTransition Table ---");
    for (String state : dfaTransitions.keySet()) {
        System.out.print("State " + state + ": ");
        Map<Character, String> transitions = dfaTransitions.get(state);
        for (Character symbol : alphabet) {
            String toState = transitions.getOrDefault(symbol,state);
            System.out.print(symbol + "->" + toState + "  ");
        }
        System.out.println();
    }
    System.out.println("\nFinal States: " + finalStates);
}
 
}


