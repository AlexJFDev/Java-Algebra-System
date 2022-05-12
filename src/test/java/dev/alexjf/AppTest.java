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
        //System.out.println(Expression.format("('a'-sin('b'*('d'*'e')))*('c')"));
        //System.out.println(Expression.format("sin('b'*('d'*'e'))"));
        //assertTrue("test one", Expression.format("(2-sin(4(2-3)))(3)").equals("(2-sin(4*(2-3)))*3"));
        System.out.println(Expression.format("('a'-sin('b'('d'*'e')))('c')"));
    }
}
