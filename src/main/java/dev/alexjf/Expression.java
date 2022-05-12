package dev.alexjf;

import java.util.ArrayList;

public class Expression {
    String expressionRaw;
    String expressionFormatted;
    String expressionExpanded;
    String expressionFactored;

    /**
     * This constructor takes the string that is meant to represent the expression. It first formats the expression to be compatible with other methods.
     * Then It factors and expands that expression. Other 
     * @param expressionAsString
     */
    public Expression(String expressionAsString){
        expressionRaw = expressionAsString;
        expressionFormatted = format(expressionAsString);
        expressionFactored = factor(expressionFormatted);
        expressionExpanded = expand(expressionFormatted);
    }

    /** 
     * This method takes an inputString that represents a mathematical expression and formats it to be more compatible with JAS
     * It removes extra parenthesis and adds * where they are needed such as if you had (3)(2+4).
     * @param inputString The mathematical expression represented as a string
     * @return String
     */
    public static String format(String inputString){
        // setup for the array that will be worked with
        char[] inputStringCharArray = inputString.toCharArray();
        ArrayList<Character> inputStringCharacterArrayList = new ArrayList<>();
        // the extra space at the start and end help with logic later on
        inputStringCharacterArrayList.add(' ');
        for(char currentChar: inputStringCharArray){
            if(currentChar != ' '){
                inputStringCharacterArrayList.add(currentChar);
            }
        }
        inputStringCharacterArrayList.add(' ');
        // All these variables are needed in the for loop
        int parenthesisDepth = 0;
        int parenthesisSetStartingIndex = -1;
        int parenthesisSetEndingIndex = -1;
        char leftSideOperation = ' ';
        char rightSideOperation = ' ';
        char insideOperation = ' ';
        boolean isInVariableReference = false;
        boolean isInFunctionReference = false;
        // since the first character and last characters in the arrayList are the spaces that were added earlier they can be skipped over
        for(int currentArrayListIndex = 1; currentArrayListIndex < inputStringCharacterArrayList.size() - 1; currentArrayListIndex++){
            /* the currentChar is the character at the currentArrayListIndex. 
            First it's checked to see if it's a ' which would mean a variable is being referenced and the variable reference bool is toggled
            Next that it's checked to see if it's referencing a function (basically letters not enclosed in ')
            Keep in mind that the constants PI and E are treated like functions*/
            char currentChar = inputStringCharacterArrayList.get(currentArrayListIndex);
            if(currentChar == '\''){
                isInVariableReference = !isInVariableReference;
            }
            if(!(Character.isDigit(currentChar) || currentChar == '\'' || currentChar == '(' || currentChar == ')' || currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') && !isInVariableReference){
                isInFunctionReference = true;
            }
            // Since a variable could include parenthesis there should be a check to make sure theres no variable before parenthesis are processed
            if(!isInVariableReference){
                /**
                 * If the current character is a ( then it is checked if there is a function reference since functions always need to be followed by () 
                 * if theres not a function reference then the location of the parenthesis is saved and a variable keeping track of parenthesis depth is incremented. 
                 * This depth variable helps deal with nested parenthesis.
                 * There is also logic for when you have a(b) to add the implied *.
                 * The ) works similarly.
                 */
                if(currentChar == '('){
                    if(!isInFunctionReference){
                        System.out.println("hit ( without func");
                        char previousChar = inputStringCharacterArrayList.get(currentArrayListIndex - 1);
                        if(previousChar != '+' && previousChar != '-' && previousChar != '*' && previousChar != '/' && previousChar != ' ' && previousChar != '('){
                            inputStringCharacterArrayList.add(currentArrayListIndex - 1, '*');
                            System.out.println("adding *");
                        }
                        parenthesisDepth++;
                        if(parenthesisDepth == 1){
                            parenthesisSetStartingIndex = currentArrayListIndex;
                        }
                    } else{
                        parenthesisDepth++;
                        isInFunctionReference = false;
                    }
                } else if(currentChar == ')'){
                    System.out.println("hit )");
                    char nextChar = inputStringCharacterArrayList.get(currentArrayListIndex + 1);
                    if(nextChar != '+' && nextChar != '-' && nextChar != '*' && nextChar != '/' && nextChar != ' ' && nextChar != ')'){
                        inputStringCharacterArrayList.add(currentArrayListIndex + 1, '*');
                    }
                    parenthesisDepth--;
                    parenthesisDepth = Math.max(0, parenthesisDepth);
                    if(parenthesisDepth == 0 && parenthesisSetStartingIndex != -1){
                        parenthesisSetEndingIndex = currentArrayListIndex;
                    }
                }
                if(parenthesisDepth != 0 && (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') && (insideOperation != '+' && insideOperation != '-')){
                    insideOperation = currentChar;
                }
            }
            /** 
             * The index variables for the parenthesis are checked to see if they are not their initial value of -1
             * -1 is the initial value because it is impossible to be the location of any parenthesis
             */ 
            if(parenthesisSetStartingIndex != -1 && parenthesisSetEndingIndex != -1){
                System.out.println("hit set");
                // variable setup
                int leftSideOperationPriority = 0;
                int rightSideOperationPriority = 0;
                int insideOperationPriority = 5;
                leftSideOperation = inputStringCharacterArrayList.get(parenthesisSetStartingIndex - 1);
                rightSideOperation = inputStringCharacterArrayList.get(parenthesisSetEndingIndex + 1);
                // set the priority for the operation on the left side of the parenthesis set
                if(leftSideOperation == '+'){
                    leftSideOperationPriority = 1;
                } else if (leftSideOperation == '-'){
                    leftSideOperationPriority = 2;
                } else if (leftSideOperation == '*'){
                    leftSideOperationPriority = 3;
                } else if (leftSideOperation == '/'){
                    leftSideOperationPriority = 4;
                }
                // set the priority for the operation on the left side of the parenthesis set
                // This one is different from the left side one because of the way - and / work with PEMDAS
                if(rightSideOperation == '+' || rightSideOperation == '-'){
                    rightSideOperationPriority = 1;
                } else if (rightSideOperation == '*' || rightSideOperation == '/'){
                    rightSideOperationPriority = 3;
                }
                // set the priority for the operation on the inside of the parenthesis set
                if(insideOperation == '+' || insideOperation == '-'){
                    insideOperationPriority = 1;
                } else if (insideOperation == '*' || insideOperation == '/'){
                    insideOperationPriority = 3;
                }
                // check out this stackoverflow link to understand whats happening: https://stackoverflow.com/a/18403396
                if(insideOperationPriority < leftSideOperationPriority || insideOperationPriority < rightSideOperationPriority){
                    currentArrayListIndex = parenthesisSetStartingIndex;
                    System.out.println("keeping set");
                }else{
                    inputStringCharacterArrayList.remove(parenthesisSetEndingIndex);
                    inputStringCharacterArrayList.remove(parenthesisSetStartingIndex);
                    currentArrayListIndex = parenthesisSetStartingIndex - 1;
                    System.out.println("removing set");
                }
                // reset all the for loop variables
                parenthesisDepth = 0;
                parenthesisSetStartingIndex = -1;
                parenthesisSetEndingIndex = -1;
                leftSideOperation = ' ';
                rightSideOperation = ' ';
                insideOperation = ' ';
                leftSideOperationPriority = 0;
                rightSideOperationPriority = 0;
                insideOperationPriority = 0;
            }
        }
        // Build the characterArrayList back into a string and then return the string
        StringBuilder outputStringBuilder = new StringBuilder(inputStringCharacterArrayList.size());
        inputStringCharacterArrayList.remove(0);
        inputStringCharacterArrayList.remove(inputStringCharacterArrayList.size() - 1);
        for(Character currentCharacter: inputStringCharacterArrayList)
        {
            outputStringBuilder.append(currentCharacter);
        }
        return outputStringBuilder.toString();
    }

    public static String expand(String inputString){
        return "";
    }

    public static String factor(String inputString){
        return "";
    }
}
