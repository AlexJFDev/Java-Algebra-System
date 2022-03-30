package dev.alexjf;

import java.util.Hashtable;
import java.io.Serializable;
import java.lang.Math;
/**
 * The Algebra Environment is how the user can do Algebra. <p>
 * It stores all the variables, expressionHashtable, and functions they've defined. <p>
 * It can also be serialized so that environments can be saved across sessions or even shared with others. <p>
 */
public class AlgebraEnvironment implements Serializable{
    // Hashtable for constants like PI or E that would be used frequently
    public Hashtable<String, String> constantHashTable = new Hashtable<>();;
    // Hashtable for functions that should be built in like sin() or cos(). I'm not sure how functions will work.
    public Hashtable<String, String> defaultFunctionHashTable = new Hashtable<>();;
    // Whether or not the system is in radian mode for trig functions. If it is not in radian mode it is in degree mode.
    public Boolean isInRadians = true;

    // Expressions defined by the user. Expressions will probably have their own class??
    public Hashtable<String, Expression> expressionHashtable;
    // Variables defined by the user. Variables will probably have their own class??
    public Hashtable<String, String> variables;
    // Functions defined by the user. I'm not sure how they'll work.
    public Hashtable<String, String> userFunctions;

    
    /**
     * Constructor for the Algebra Environment <p>
     * It initializes the populates the constant and default function hashtables.
     */
    public AlgebraEnvironment(){
        populateConstants();
        populateDefaultFunctions();
    }

    /**
     * This method populates the constantHashTable.
     */
    public void populateConstants(){
        constantHashTable.put("pi", "3.14159265358979323846");
        constantHashTable.put("e", "2.7182818284590452354");

    }

    /**
     * This method populates the defaultFunctionHashTable.
     */
    public void populateDefaultFunctions(){
        defaultFunctionHashTable.put("sum", "");
        defaultFunctionHashTable.put("difference", "");
        defaultFunctionHashTable.put("product", "");
        defaultFunctionHashTable.put("quotient", "");
        defaultFunctionHashTable.put("power", "");
        defaultFunctionHashTable.put("root", "");
    }

    /**
     * Add a new item to the expresison hashtable.
     * @param expressionName
     * @param expressionAsString
     * @return Doesn't return anything
     * @throws 
     */
    public void putExpression(String expressionName, String expressionAsString){
        Expression expression = new Expression(expressionAsString);
        expressionHashtable.put(expressionName, expression);
    }

    public void putsExpression(String expressionName, Expression expression){
        expressionHashtable.put(expressionName, expression);
    }
}
