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
        Term testTermOne = new Term("0+0");
        if(!testTermOne.termAsFormattedString.equals("sum(0,0)")) assertTrue("test one fail", false);
        assertTrue("all tests passed", true);
    }
}
