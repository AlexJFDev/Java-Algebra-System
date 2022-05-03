package dev.alexjf;

import java.util.ArrayList;

public class Expression {
    String rawExpressionAsSting;
    String expressionAsSting;
    String expressionAsSimplifiedString;

    public Expression(String expressionAsString){
        rawExpressionAsSting = expressionAsString;
        expressionAsString = removeExtraParenthesis(expressionAsString);
    }

    
    /** 
     * @param inputString
     * @return String
     */
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
                parenthesisDepth--;
                parenthesisDepth = Math.max(0, parenthesisDepth);
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
                    leftSideOperation = inputStringCharacterArrayList.get(parenthesisSetStartingIndex - 1);
                }
                if(parenthesisSetEndingIndex != inputStringCharacterArrayList.size() - 1){
                    rightSideOperation = inputStringCharacterArrayList.get(parenthesisSetEndingIndex + 1);
                }
                if(leftSideOperation == '+'){
                    leftSideOperationPriority = 1;
                } else if (leftSideOperation == '-'){
                    leftSideOperationPriority = 2;
                } else if (leftSideOperation == '*'){
                    leftSideOperationPriority = 3;
                } else if (leftSideOperation == '/'){
                    leftSideOperationPriority = 4;
                }
                if(rightSideOperation == '+' || rightSideOperation == '-'){
                    rightSideOperationPriority = 1;
                } else if (rightSideOperation == '*' || rightSideOperation == '/'){
                    rightSideOperationPriority = 3;
                }
                if(insideOperation == '+' || insideOperation == '-'){
                    insideOperationPriority = 1;
                } else if (insideOperation == '*' || insideOperation == '/'){
                    insideOperationPriority = 3;
                }
                if(insideOperationPriority < leftSideOperationPriority || insideOperationPriority < rightSideOperationPriority){
                    currentArrayListIndex = parenthesisSetStartingIndex;
                }else{
                    inputStringCharacterArrayList.remove(parenthesisSetEndingIndex);
                    inputStringCharacterArrayList.remove(parenthesisSetStartingIndex);
                    currentArrayListIndex = parenthesisSetStartingIndex - 1;
                }
                parenthesisDepth = 0;
                parenthesisSetStartingIndex = -1;
                parenthesisSetEndingIndex = -1;
                leftSideOperation = ' ';
                rightSideOperation = ' ';
                insideOperation = ' ';
            }
        }
        StringBuilder builder = new StringBuilder(inputStringCharacterArrayList.size());
        for(Character currentCharacter: inputStringCharacterArrayList)
        {
            builder.append(currentCharacter);
        }
        return builder.toString();
    }
}
