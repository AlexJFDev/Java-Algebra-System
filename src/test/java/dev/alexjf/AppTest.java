package dev.alexjf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * I've never written tests before
     */
    @Test
    public void shouldAnswerWithTrue(){
        System.out.println(TestMethods.removeExtraParenthesis("(1+2-3*4/5)"));
        /*System.out.println("Performing True Test One");
        assertTrue("true test one", TestMethods.shouldRemoveParenthesis('-', '-', '*'));
        System.out.println("Performing False Test One");
        assertFalse("false test one", TestMethods.shouldRemoveParenthesis('-', '*', '-'));
        assertFalse("false test two", TestMethods.shouldRemoveParenthesis(null, '*', '+'));*/
    }
}
