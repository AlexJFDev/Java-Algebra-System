package dev.alexjf;

import java.util.ArrayList;

public class Expression {
    String expressionAsFormattedString;
    Term expressionAsTerm;
    public Expression(String expressionAsString){
        char[] expressionAsCharArray = expressionAsString.toCharArray();
        int parenthesisDepth = 0;
        for(int currentCharacterIndex = expressionAsCharArray.length - 1; currentCharacterIndex >= 0; currentCharacterIndex--){
            char currentCharacter = expressionAsCharArray[currentCharacterIndex];
            if(currentCharacter == ')'){
                parenthesisDepth = parenthesisDepth + 1;
            } else if (currentCharacter == '('){
                parenthesisDepth = parenthesisDepth - 1;
                if(parenthesisDepth < 0) throw new IllegalArgumentException("Unmatched '(' at index " + currentCharacter);
            } else if(parenthesisDepth == 0){
                if(currentCharacter == '+'){
                    String rightSide = expressionAsString.substring(0, currentCharacterIndex);
                    String leftSide = expressionAsString.substring(currentCharacterIndex + 1);
                    expressionAsFormattedString = "sum("+rightSide+","+leftSide+")";
                } else if(currentCharacter == '-'){
                    String rightSide = expressionAsString.substring(0, currentCharacterIndex);
                    String leftSide = expressionAsString.substring(currentCharacterIndex + 1);
                    expressionAsFormattedString = "difference("+rightSide+","+leftSide+")";
                }
            }
        }
        System.out.println(expressionAsFormattedString);
    }
}
