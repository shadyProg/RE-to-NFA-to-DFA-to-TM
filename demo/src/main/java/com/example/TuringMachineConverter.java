package com.example;

public class TuringMachineConverter {
    DFAConstructor dfaConstructor ;

  public TuringMachineConverter(DFAConstructor dfaConstructor) {
        this.dfaConstructor = dfaConstructor;
    }
    public void printTuringMachineRules() {
        System.out.println("\n--- Turing Machine Rules ---");

       
        System.out.println("(START, <) → (" + dfaConstructor.getStartState() + ", <, R)");

       
        for (var from : dfaConstructor.getDFATransitions().keySet()) {
            for (var symbol :  dfaConstructor.getDFATransitions().get(from).keySet()) {
                String to =  dfaConstructor.getDFATransitions().get(from).get(symbol);
                System.out.println("(" + from + ", " + symbol + ") → (" + to + ", " + symbol + ", R)");
            }
            
        }

       
        for (String accept :dfaConstructor.getFinalStates()) {
            System.out.println("(" + accept + ", #) → ("+accept+", #, Y)");
        }

        
        System.out.println("(any_state, #) → (same_state , #, N)");
    }

   
}