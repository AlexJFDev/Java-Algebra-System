package dev.alexjf;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println("Test One");
        if(TestMethods.removeExtraParenthesis("(a+b+c)") != "a+b+c") assertTrue("test one fail", false);
        assertTrue("all tests passed", true);
    }
}
