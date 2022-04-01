package dev.alexjf;

import java.util.ArrayList;
//import java.util.HashMap;

public class TestMethods {
    /**
     * This method removes extra parenthesis from a string that is a mathematical expression. It assumes that the expression is valid. Put variables in '' single quotes.
     * @param inputString the expression, expressed as a string, to have parenthesis removed from it.
     * @return The inputString with extra parenthesis removed.
     */
    public static String removeExtraParenthesis(String inputString){
        // Lookup Table for operation priorities.
        /*HashMap<Character, Integer> operationPriorityLookupTable = new HashMap<Character,Integer>();
        operationPriorityLookupTable.put('^', 1);
        operationPriorityLookupTable.put('*', 2);
        operationPriorityLookupTable.put('/', 3);
        operationPriorityLookupTable.put('+', 4);
        operationPriorityLookupTable.put('-', 5);
        operationPriorityLookupTable.put(null, 6);*/
        // The String to be returned
        String outputString = "";
        // Convert the inputString to an array of chars
        char[] inputStringAsCharArray = inputString.toCharArray();
        // Create ArrayList to store the inputString as Characters
        ArrayList<Character> inputStringAsCharacterArrayList = new ArrayList<>();
        // For loop to populate the inputString Character ArrayList
        for(char currentChar: inputStringAsCharArray){
            inputStringAsCharacterArrayList.add(currentChar);
        }
        // Variable to track the depth inside parenthesis
        int parenthesisDepth = 0;
        // Variable to track the index of the opening parenthesis
        int openingParenthesisIndex = -1;
        // Variable to track the index of the closing parenthesis
        int closingParenthesisIndex = -1;
        // Variable to track the priority of the operation inside parenthesis
        int innerOperationPriority = 0;
        // Variable to track what the inner operation actually is
        char innerOperation = ' ';

        // for loop to iterate over the entire list
        for(int currentCharIndex = 0; currentCharIndex < inputStringAsCharacterArrayList.size(); currentCharIndex++){
            // the current char
            char currentChar = inputStringAsCharacterArrayList.get(currentCharIndex);
            // check if the current character is '(' if it is increase the parenthesis depth then if the parenthesis is not nested set the opening parenthesis index
            if(currentChar == '('){
                parenthesisDepth += 1;
                if(parenthesisDepth == 1){
                    openingParenthesisIndex = currentCharIndex;
                }
            }
            // check if the current character is ')' if it is decrease the parenthesis depth then if the parenthesis is not nested set the closing parenthesis index
            if(currentChar == ')'){
                parenthesisDepth -= 1;
                if(parenthesisDepth == 0){
                    closingParenthesisIndex = currentCharIndex;
                }
            }
            // Check if the pointer is currently only one parenthesis set in
            if(parenthesisDepth == 1){
                // If the current character is '^' (power) and there hasn't already been a lower priority operation set the inner operation to '^' and set the inner operation priority to 1
                if(currentChar == '^' && innerOperationPriority < 1){
                    innerOperation = '^';
                    innerOperationPriority = 1;
                }
                // If the current character is '*' (product) or '/' (quotient) and there hasn't already been a lower priority operation set the inner operation to '*' or '/' and set the inner operation priority to 2
                if((currentChar == '*' || currentChar == '/') && innerOperationPriority < 2){
                    innerOperation = currentChar;
                    innerOperationPriority = 2;
                }
                // If the current character is '+' (sum) or '-' (difference) and there hasn't already been a lower priority operation set the inner operation to '+' or '-' and set the inner operation priority to 3
                if((currentChar == '+' || currentChar == '-') && innerOperationPriority < 3){
                    innerOperation = currentChar;
                    innerOperationPriority = 3;
                }
            }
            // if the opening and closing parenthesis indexes are not their default value, which is -1, enter this for loop
            if(openingParenthesisIndex != -1 && closingParenthesisIndex != -1){
                Character rightSideOperation = null;
                Character leftSideOperation = null;
                // Set the value of the operation to the right of the parenthesis and if there isn't one then leave it as null.
                if(openingParenthesisIndex != 0){ 
                    rightSideOperation = inputStringAsCharacterArrayList.get(openingParenthesisIndex - 1);
                }
                // Set the value of the operation to the left of the parenthesis and if there isn't one then leave it as null.
                if(closingParenthesisIndex != inputStringAsCharacterArrayList.size() + 1){ 
                    leftSideOperation = inputStringAsCharacterArrayList.get(closingParenthesisIndex + 1);
                }
                // If there is no operation on either side of the parenthesis then they can be removed
                if(rightSideOperation == null && leftSideOperation == null){
                    // Move the current index back to the start of the parenthesis set that was just checked.
                    currentCharIndex = openingParenthesisIndex;
                    // Remove the opening parenthesis of the set just checked
                    inputStringAsCharacterArrayList.remove(openingParenthesisIndex);
                    // Remove the closing parenthesis of the set just checked
                    inputStringAsCharacterArrayList.remove(closingParenthesisIndex);
                }
                
            }
        }
        StringBuilder outputStringBuilder = new StringBuilder(inputStringAsCharacterArrayList.size());
        for(char currentChar: inputStringAsCharacterArrayList){
            outputStringBuilder.append(currentChar);
        }
        outputString = outputStringBuilder.toString();
        return outputString;
    }
}
