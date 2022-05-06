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
        assertTrue("test one", Expression.format("(2'x'-4'x')(3)").equals("(2*'x'-4*'x')*3"));
    }
}
