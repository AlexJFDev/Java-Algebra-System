package dev.alexjf;

import java.util.ArrayList;

public class TestMethods {

    public static String removeExtraParenthesis(String inputString){
        char[] inputStringCharArray = inputString.toCharArray();
        ArrayList<Character> inputStringCharacterArrayList = new ArrayList<>();
        for(char currentChar: inputStringCharArray){
            inputStringCharacterArrayList.add(currentChar);
        }
        int parenthesisDepth = 0;
        int parenthesisSetStartingIndex = -1;
        int parenthesisSetEndingIndex = -1;
        char leftSideOperation = ' ';
        char rightSideOperation = ' ';
        char insideOperation = ' ';
        for(int currentArrayListIndex = 0; currentArrayListIndex < inputStringCharacterArrayList.size(); currentArrayListIndex++){
            char currentChar = inputStringCharacterArrayList.get(currentArrayListIndex);
            if(currentChar == '('){
                parenthesisDepth++;
                if(parenthesisDepth == 1){
                    parenthesisSetStartingIndex = currentArrayListIndex;
                }
            } else if(currentChar == ')'){
                parenthesisDepth = Math.max(0, parenthesisDepth--);
                if(parenthesisDepth == 0){
                    parenthesisSetEndingIndex = currentArrayListIndex;
                }
            }
            if(parenthesisDepth != 0 && (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') && (insideOperation != '+' && insideOperation != '-')){
                insideOperation = currentChar;
            }
            if(parenthesisSetStartingIndex != -1 && parenthesisSetEndingIndex != -1){
                int leftSideOperationPriority = 0;
                int rightSideOperationPriority = 0;
                int insideOperationPriority = 0;
                if(parenthesisSetStartingIndex != 0){
                    leftSideOperation = inputStringCharArray[parenthesisSetStartingIndex];
                }
                if(parenthesisSetEndingIndex != inputStringCharArray.length){
                    rightSideOperation = inputStringCharArray[parenthesisSetEndingIndex];
                }
                if(leftSideOperation == '+' || leftSideOperation == '-'){
                    leftSideOperationPriority = 1;
                } else if (leftSideOperation == '*' || leftSideOperation == '/'){
                    leftSideOperationPriority = 2;
                }
                if(rightSideOperation == '+' || rightSideOperation == '-'){
                    rightSideOperationPriority = 1;
                } else if (rightSideOperation == '*' || rightSideOperation == '/'){
                    rightSideOperationPriority = 2;
                }
                if(insideOperation == '+' || insideOperation == '-'){
                    insideOperationPriority = 1;
                } else if (insideOperation == '*' || insideOperation == '/'){
                    insideOperationPriority = 2;
                }
                if(insideOperationPriority < leftSideOperationPriority || insideOperationPriority < rightSideOperationPriority){
                    
                }else{
                    inputStringCharacterArrayList.remove(parenthesisSetEndingIndex);
                    inputStringCharacterArrayList.remove(parenthesisSetStartingIndex);
                    currentArrayListIndex = parenthesisSetStartingIndex;
                }
                parenthesisDepth = 0;
                parenthesisSetStartingIndex = -1;
                parenthesisSetEndingIndex = -1;
                leftSideOperation = ' ';
                rightSideOperation = ' ';
                insideOperation = ' ';

            }
        }
        return "";
    }
    
    /**
     * This method removes extra parenthesis from a string that is a mathematical expression. 
     * It assumes that the expression is valid. Variables should be enclosed in single quotes
     * @param inputString the expression, expressed as a string, to have parenthesis removed from it.
     * @return The inputString with extra parenthesis removed.
     */
    /*public static String removeExtraParenthesis(String inputString){
        // The String to be returned
        String outputString = "";
        // Convert the inputString to an array of chars
        char[] inputStringCharArray = inputString.toCharArray();
        // Create ArrayList to store the inputString as Characters
        ArrayList<Character> inputStringAsCharacterArrayList = new ArrayList<>();
        // For loop to populate the inputString Character ArrayList
        for(char currentChar: inputStringCharArray){
            if(currentChar != ' ') inputStringAsCharacterArrayList.add(currentChar);
        }
        // Variable to track the depth inside parenthesis
        int parenthesisDepth = 0;
        // Variable to track the index of the opening parenthesis
        int openingParenthesisIndex = -1;
        // Variable to track the index of the closing parenthesis
        int closingParenthesisIndex = -1;
        // Variable to track the priority of the operation inside parenthesis
        int insideOperationPriority = 0;
        // Variable to track what the inner operation actually is
        char insideOperation = ' ';
        // for loop to iterate over the entire list
        for(int currentCharIndex = 0; currentCharIndex < inputStringAsCharacterArrayList.size(); currentCharIndex++){
            // get the current char
            char currentChar = inputStringAsCharacterArrayList.get(currentCharIndex);
            // check if the current character is '(' if it is increase the parenthesis depth then if the parenthesis is not nested set the opening parenthesis index
            if(currentChar == '('){
                parenthesisDepth++;
                if(parenthesisDepth == 1){
                    openingParenthesisIndex = currentCharIndex;
                }
            }
            // check if the current character is ')' if it is decrease the parenthesis depth then if the parenthesis is not nested set the closing parenthesis index
            if(currentChar == ')' && parenthesisDepth != 0){
                parenthesisDepth--;
                if(parenthesisDepth == 0){
                    closingParenthesisIndex = currentCharIndex;
                }
            }
            // Check if the pointer is currently only one parenthesis set in
            if(parenthesisDepth == 1){
                // If the current character is '^' (power) and there hasn't already been a lower priority operation set the inner operation to '^' and set the inner operation priority to 1
                if(currentChar == '^' && insideOperationPriority < 1){
                    insideOperation = '^';
                    insideOperationPriority = 1;
                }
                // If the current character is '*' (product) or '/' (quotient) and there hasn't already been a lower priority operation set the inner operation to '*' or '/' and set the inner operation priority to 2
                if((currentChar == '*' || currentChar == '/') && insideOperationPriority < 2){
                    insideOperation = currentChar;
                    insideOperationPriority = 2;
                }
                // If the current character is '+' (sum) or '-' (difference) and there hasn't already been a lower priority operation set the inner operation to '+' or '-' and set the inner operation priority to 3
                if((currentChar == '+' || currentChar == '-') && insideOperationPriority < 3){
                    insideOperation = currentChar;
                    insideOperationPriority = 3;
                }
            }
            // if the opening and closing parenthesis indexes are not their default value, which is -1, enter this if
            if(openingParenthesisIndex != -1 && closingParenthesisIndex != -1){
                char rightSideOperation = ' ';
                char leftSideOperation = ' ';
                // Set the value of the operation to the right of the parenthesis and if there isn't one then leave it as null.
                if(openingParenthesisIndex != 0){ 
                    rightSideOperation = inputStringAsCharacterArrayList.get(openingParenthesisIndex - 1);
                }
                // Set the value of the operation to the left of the parenthesis and if there isn't one then leave it as null.
                if(closingParenthesisIndex != inputStringAsCharacterArrayList.size() - 1){
                    leftSideOperation = inputStringAsCharacterArrayList.get(closingParenthesisIndex + 1);
                }
                // If there is no operation on either side of the parenthesis then they can be removed
                System.out.println(insideOperation);
                int leftSidePriority;
                int rightSidePriority;
                int insidePriority;
                if(leftSideOperation == '*' || leftSideOperation == '/'){
                    leftSidePriority = 2;
                } else if(leftSideOperation == '+' || leftSideOperation == '-'){
                    leftSidePriority = 1;
                } else {
                    leftSidePriority = 0;
                }
                if(rightSideOperation == '*' || rightSideOperation == '/'){
                    rightSidePriority = 2;
                } else if(rightSideOperation == '+' || rightSideOperation == '-'){
                    rightSidePriority = 1;
                } else {
                    rightSidePriority = 0;
                }if(insideOperation == '*' || insideOperation == '/'){
                    insidePriority = 2;
                } else if(insideOperation == '+' || insideOperation == '-'){
                    insidePriority = 1;
                } else {
                    insidePriority = 0;
                }
                //if(shouldRemoveParenthesis(leftSideOperation, rightSideOperation, insideOperation)){
                if(insidePriority < leftSidePriority || insidePriority < rightSidePriority){
                    // Move the current index back to the start of the parenthesis set that was just checked.
                    currentCharIndex = openingParenthesisIndex + 1;
                    // Reset the parenthesis indexes to -1 
                    openingParenthesisIndex = -1;
                    closingParenthesisIndex = -1;
                    // Reset inner operation priority & operation
                    insideOperationPriority = 0;
                    insideOperation = ' ';
                    // Go back to start of for loop
                    continue;
                } else{
                    // Move the current index back to the start of the parenthesis set that was just checked.
                    currentCharIndex = openingParenthesisIndex - 1;
                    // Remove the closing parenthesis of the set just checked
                    inputStringAsCharacterArrayList.remove(closingParenthesisIndex);
                    // Remove the opening parenthesis of the set just checked
                    inputStringAsCharacterArrayList.remove(openingParenthesisIndex);
                    // Reset the parenthesis indexes to -1 
                    openingParenthesisIndex = -1;
                    closingParenthesisIndex = -1;
                    // Reset inner operation priority & operation
                    insideOperationPriority = 0;
                    insideOperation = ' ';
                    // Go back to start of for loop
                    continue;
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
    
    /**
     * Checks whether or not parenthesis should be removed based on the operations to the right, to the left, and inside the parenthesis
     * 
     * @param leftSideOperation The operation that is to the left of the parenthesis.
     * @param rightSideOperation The operation to the right of the parenthesis
     * @param insideOperation The operation inside the parenthesis
     * @return Returns true if the parenthesis should be removed otherwise the method returns true
     */
    /*public static boolean shouldRemoveParenthesis(Character leftSideOperation, Character rightSideOperation, Character insideOperation){
        if(insideOperation == null) return true;
        if(leftSideOperation == null && rightSideOperation == null) return true;
        if(leftSideOperation == null){
            if((insideOperation.equals('+') || insideOperation.equals('-')) && (rightSideOperation.equals('*') || rightSideOperation.equals('/'))) return true;
            return false;
        }
        if(rightSideOperation == null){
            System.out.println("right");
            System.out.println(insideOperation);
            System.out.println(leftSideOperation);
            System.out.println(insideOperation.equals('+') || insideOperation.equals('-'));
            System.out.println(leftSideOperation.equals('*') || leftSideOperation.equals('/'));
            if((insideOperation.equals('+') || insideOperation.equals('-')) && (leftSideOperation.equals('*') || leftSideOperation.equals('/'))) return false;
            return true;
        }
        if(leftSideOperation.equals('-') && (insideOperation.equals('/') || insideOperation.equals('*'))) return true;
        if(leftSideOperation.equals('+') && (insideOperation.equals('-') || insideOperation.equals('+')) && (rightSideOperation.equals('-') || rightSideOperation.equals('+'))) return true;
        if(leftSideOperation.equals('+') && (insideOperation.equals('/') || insideOperation.equals('*'))) return true;
        if(leftSideOperation.equals('*') && (insideOperation.equals('/') || insideOperation.equals('*'))) return true;
        return false;
    }*/
}
