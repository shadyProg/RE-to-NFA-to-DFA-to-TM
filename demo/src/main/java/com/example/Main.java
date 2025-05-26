package com.example;
 import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
       
        
        // Validate input
      boolean flage = true;
       
      do{
       try {
         Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a regular expression (e.g., a.b|c*, c.(a.b)* ,a.c): ");
        String input = scanner.nextLine();
        
       
       // String input = "1.0*"; 
        Token token = new Token(input);
        char[] postfix = InfixToPostfix.convert(token.getTokens());
        NFA nfa = NFAConstructor.buildFromPostfix(postfix);

        System.out.println("NFA constructed successfully from the input: " + input);
        DFAConstructor dfaConstructor = new DFAConstructor(nfa);
        TuringMachineConverter turingMachineConverter = new TuringMachineConverter(dfaConstructor);

        turingMachineConverter.printTuringMachineRules();
        dfaConstructor.print();
        dfaConstructor.printAllTransitions();

        System.out.println("what contunie? (y/n)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("n")) 
            flage = false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }} while (flage);
       


    }
}