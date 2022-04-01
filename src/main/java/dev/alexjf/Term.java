package dev.alexjf;

public class Term {
    Term rightSideTerm;
    Term leftSideTerm;
    String function;
    String termAsFormattedString;

    public Term(String termAsString){
        /*char[] termAsCharArray = termAsString.toCharArray();
        Stack<Character> termAsStack = new Stack<>();
        for(Character currentCharacter: termAsCharArray){
            termAsStack.add(currentCharacter);
        }
        boolean inVariable = false;
        while(!termAsStack.isEmpty()){
            char currentCharacter = termAsStack.pop();
        }
        
        while(termAsCharArray[0] == '(' && termAsCharArray[termAsCharArray.length - 1] == ')'){
            termAsString = termAsString.substring(1, termAsString.length() - 1);
            termAsCharArray = termAsString.toCharArray();
            System.out.println(termAsString);
        }
        int parenthesisDepth = 0;
        for(int currentCharacterIndex = termAsCharArray.length - 1; currentCharacterIndex >= 0; currentCharacterIndex--){
            char currentCharacter = termAsCharArray[currentCharacterIndex];
            if(currentCharacter == ')'){
                parenthesisDepth = parenthesisDepth + 1;
            } else if (currentCharacter == '('){
                parenthesisDepth = parenthesisDepth - 1;
                if(parenthesisDepth < 0) throw new IllegalArgumentException("Unmatched '(' at index " + currentCharacter);
            } else if(parenthesisDepth == 0){
                if(currentCharacter == '+'){
                    String rightSideString = termAsString.substring(0, currentCharacterIndex);
                    String leftSideString = termAsString.substring(currentCharacterIndex + 1);
                    rightSideTerm = new Term(rightSideString);
                    leftSideTerm = new Term(leftSideString);
                    function = "sum";
                    termAsFormattedString = "sum("+rightSideString+","+leftSideString+")";
                } else if(currentCharacter == '-'){
                    String rightSideString = termAsString.substring(0, currentCharacterIndex);
                    String leftSideString = termAsString.substring(currentCharacterIndex + 1);
                    rightSideTerm = new Term(rightSideString);
                    leftSideTerm = new Term(leftSideString);
                    function = "difference";
                    termAsFormattedString = "difference("+rightSideString+","+leftSideString+")";
                }
            }
        }
        if(termAsFormattedString == null){
            termAsFormattedString = termAsString;
        }
        System.out.println(termAsFormattedString);
        */
    }

    public Term(Term rightSideTerm, Term leftSideTerm, String function){
        this.rightSideTerm = rightSideTerm;
        this.leftSideTerm = leftSideTerm;
        this.function = function;
    }
}
