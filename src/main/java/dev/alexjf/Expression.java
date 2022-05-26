package dev.alexjf;

import java.util.ArrayList;

public class Expression {
    String expressionRaw;
    ArrayList<Character> expressionFormattedArrayList;
    ArrayList<Character> expressionExpandedArrayList;
    ArrayList<Character> expressionFactoredArrayList;
    String expressionFormattedString;

    /**
     * This constructor takes the string that is meant to represent the expression. It first formats the expression to be compatible with other methods.
     * Then It factors and expands that expression. Other 
     * @param expressionAsString
     */
    public Expression(String expressionAsString){
        expressionRaw = expressionAsString;
        char[] expressionAsCharArray = expressionRaw.toCharArray();
        ArrayList<Character> expressionAsCharacterArrayList = new ArrayList<>();
        for(char currentChar: expressionAsCharArray){
            if(currentChar != ' '){
                expressionAsCharacterArrayList.add(currentChar);
            }
        }
        expressionFormattedArrayList = format(expressionAsCharacterArrayList);
        expressionFormattedArrayList.remove(0);
        expressionFormattedArrayList.remove(expressionFormattedArrayList.size() - 1);
        StringBuilder expressionStringBuilder = new StringBuilder();
        for(char currentChar: expressionFormattedArrayList){
            expressionStringBuilder.append(currentChar);
        }
        expressionFormattedString = expressionStringBuilder.toString();
        expressionFactoredArrayList = factor(expressionFormattedArrayList);
        expressionExpandedArrayList = expand(expressionFormattedArrayList);
    }

    /** 
     * This method takes an inputString that represents a mathematical expression and formats it to be more compatible with JAS
     * It removes extra parenthesis and adds * where they are needed such as if you had (3)(2+4).
     * @param inputString The mathematical expression represented as a string
     * @return String
     */
    public static ArrayList<Character> format(ArrayList<Character> inputStringCharacterArrayList){
        ArrayList<Character> formattedInputStringCharacterArrayList = new ArrayList<>(inputStringCharacterArrayList);
        formattedInputStringCharacterArrayList.add(' ');
        formattedInputStringCharacterArrayList.add(0, ' ');

        // All these variables are needed in the for loop
        int parenthesisDepth = 0;
        int parenthesisSetStartingIndex = -1;
        int parenthesisSetEndingIndex = -1;
        int termStartingIndex = 1;
        int termEndingIndex = -1;
        char leftSideOperation = ' ';
        char rightSideOperation = ' ';
        char insideOperation = ' ';
        boolean isInVariableReference = false;
        //ArrayList<ArrayList<Character>> expressionTermsArrayList = new ArrayList<>();
        // since the first character and last characters in the arrayList are the spaces that were added earlier they can be skipped over
        for(int currentArrayListIndex = 1; currentArrayListIndex < formattedInputStringCharacterArrayList.size() - 1; currentArrayListIndex++){
            /* the currentChar is the character at the currentArrayListIndex. 
            First it's checked to see if it's a ' which would mean a variable is being referenced and the variable reference bool is toggled
            Next that it's checked to see if it's referencing a function (basically letters not enclosed in ')
            Keep in mind that the constants PI and E are treated like functions*/
            char currentChar = formattedInputStringCharacterArrayList.get(currentArrayListIndex);
            if(currentChar == '\''){
                System.out.println("ahh");
                isInVariableReference = !isInVariableReference;
                if(!isInVariableReference){
                    char nextChar = formattedInputStringCharacterArrayList.get(currentArrayListIndex + 1);
                    if(nextChar != '+' && nextChar != '-' && nextChar != '*' && nextChar != '/' && nextChar != ')' && nextChar != ' '){
                        formattedInputStringCharacterArrayList.add(currentArrayListIndex + 1, '*');
                        System.out.println("bb");
                    }
                }
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
                if(Character.isDigit(currentChar)){
                    char nextChar = formattedInputStringCharacterArrayList.get(currentArrayListIndex + 1);
                    if(nextChar != '+' && nextChar != '-' && nextChar != '*' && nextChar != '/' && nextChar != ')' && nextChar != ' ' && !Character.isDigit(nextChar)){
                        formattedInputStringCharacterArrayList.add(currentArrayListIndex + 1, '*');
                    }
                }
                if(currentChar == '('){
                    parenthesisDepth++;
                    if(parenthesisDepth == 1){
                        parenthesisSetStartingIndex = currentArrayListIndex;
                    }
                } else if(currentChar == ')'){
                    char nextChar = formattedInputStringCharacterArrayList.get(currentArrayListIndex + 1);
                    if(nextChar != '+' && nextChar != '-' && nextChar != '*' && nextChar != '/' && nextChar != ')' && nextChar != ' '){
                        formattedInputStringCharacterArrayList.add(currentArrayListIndex + 1, '*');
                    }
                    parenthesisDepth--;
                    parenthesisDepth = Math.max(0, parenthesisDepth);
                    if(parenthesisDepth == 0 && parenthesisSetStartingIndex != -1){
                        parenthesisSetEndingIndex = currentArrayListIndex;
                    }
                } else if(currentChar == '*' || currentChar == '/'){
                    if(termStartingIndex == -1){
                        termStartingIndex = currentArrayListIndex;
                    } else {
                        termEndingIndex = currentArrayListIndex;
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
            if(termStartingIndex != -1 && termEndingIndex != -1){
                ArrayList<Character> termArrayList = new ArrayList<>(formattedInputStringCharacterArrayList.subList(termStartingIndex + 1, termEndingIndex));
                //expressionTermsArrayList.add(termArrayList);
                for(char currentTermChar: termArrayList){
                    System.out.print(currentTermChar);
                }
                System.out.println();
                termStartingIndex = -1;
                termEndingIndex = -1;
            }
            if(parenthesisSetStartingIndex != -1 && parenthesisSetEndingIndex != -1){
                System.out.println("hit set");
                // Apply this format method to the inside of the parenthesis
                ArrayList<Character> subExpressionArrayList = new ArrayList<>(formattedInputStringCharacterArrayList.subList(parenthesisSetStartingIndex + 1, parenthesisSetEndingIndex));
                ArrayList<Character> formattedSubExpressionArrayList = format(subExpressionArrayList);
                formattedSubExpressionArrayList.remove(0);
                formattedSubExpressionArrayList.remove(formattedSubExpressionArrayList.size() - 1);
                // variable setup
                int leftSideOperationPriority = 6;
                int rightSideOperationPriority = 6;
                int insideOperationPriority = 5;
                leftSideOperation = formattedInputStringCharacterArrayList.get(parenthesisSetStartingIndex - 1);
                rightSideOperation = formattedInputStringCharacterArrayList.get(parenthesisSetEndingIndex + 1);
                // set the priority for the operation on the left side of the parenthesis set
                if(leftSideOperation == '(' || leftSideOperation == ',' || leftSideOperation == ' '){
                    leftSideOperationPriority = 0;
                } else if(leftSideOperation == '+'){
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
                if(rightSideOperation == ')' || rightSideOperation == ',' || rightSideOperation == ' '){
                    rightSideOperationPriority = 0;
                } else if(rightSideOperation == '+' || rightSideOperation == '-'){
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
                System.out.println(insideOperationPriority);
                System.out.println(leftSideOperationPriority);
                System.out.println(rightSideOperationPriority);
                // check out this stackoverflow link to better understand whats happening with logic: https://stackoverflow.com/a/18403396
                if(insideOperationPriority < leftSideOperationPriority || insideOperationPriority < rightSideOperationPriority){
                    // If true then the parenthesis should be kept
                    System.out.println("keeping set");
                    currentArrayListIndex = parenthesisSetStartingIndex + formattedSubExpressionArrayList.size() + 1;
                    removeRangeFromArrayList(formattedInputStringCharacterArrayList, parenthesisSetStartingIndex + 1, parenthesisSetEndingIndex);
                    insertArrayListIntoArrayList(formattedInputStringCharacterArrayList, formattedSubExpressionArrayList, parenthesisSetStartingIndex + 1);
                }else{
                    System.out.println("removing set");
                    currentArrayListIndex = parenthesisSetStartingIndex + formattedSubExpressionArrayList.size() - 1;
                    removeRangeFromArrayList(formattedInputStringCharacterArrayList, parenthesisSetStartingIndex, parenthesisSetEndingIndex + 1);
                    insertArrayListIntoArrayList(formattedInputStringCharacterArrayList, formattedSubExpressionArrayList, parenthesisSetStartingIndex);
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
        return formattedInputStringCharacterArrayList;
    }

    
    public static ArrayList<Character> expand(ArrayList<Character> inputStringCharacterArrayList){
        return new ArrayList<>();
    }

    public static ArrayList<Character> factor(ArrayList<Character> inputStringCharacterArrayList){
        return new ArrayList<>();
    }

    /**
     * Remove all elements from the inputArrayList from the startingIndex (inclusive) to the
     * endingIndex (exclusive).
     * 
     * @param inputArrayList The ArrayList that you want to remove elements from.
     * @param startingIndex The index of the first element to be removed.
     * @param endingIndex The index of the last element to be removed.
     * @return An ArrayList of Objects
     */
    public static void removeRangeFromArrayList(ArrayList<?> inputArrayList, int startingIndex, int endingIndex){
        for(int i = 0; i < endingIndex - startingIndex; i++){
            inputArrayList.remove(startingIndex);
        }
    }
    
    public static <E> void insertArrayListIntoArrayList(ArrayList<E> mainArrayList, ArrayList<E> subArrayList, int insertPosition){
        for(int currentSubArrayListIndex = subArrayList.size(); currentSubArrayListIndex > 0; currentSubArrayListIndex--){
            mainArrayList.add(insertPosition, subArrayList.get(currentSubArrayListIndex - 1));
        }
    }
}
