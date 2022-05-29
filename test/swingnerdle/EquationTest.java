/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package swingnerdle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MUHAMMEDERENŞEKKELİ
 */
public class EquationTest {
    
    public EquationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of generateEquation method, of class Equation.
     */
    @Test
    public void testGenerateEquation() {
        System.out.println("generateEquation");
        Equation instance = new Equation();
        String expResult = "";
        String result = instance.generateEquation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toInt method, of class Equation.
     */
    @Test
    public void testToInt() {
        System.out.println("toInt");
        String s = "";
        Equation instance = new Equation();
        int expResult = 0;
        int result = instance.toInt(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateValue method, of class Equation.
     */
    @Test
    public void testCalculateValue() {
        System.out.println("calculateValue");
        int a = 0;
        int b = 0;
        char op = ' ';
        Equation instance = new Equation();
        int expResult = 0;
        int result = instance.calculateValue(a, b, op);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateEquation method, of class Equation.
     */
    @Test
    public void testCalculateEquation() {
        System.out.println("calculateEquation");
        String eq = "";
        Equation instance = new Equation();
        String expResult = "";
        String result = instance.calculateEquation(eq);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEquationValid method, of class Equation.
     */
    @Test
    public void testIsEquationValid() {
        System.out.println("isEquationValid");
        String equation = "";
        Equation instance = new Equation();
        boolean expResult = false;
        boolean result = instance.isEquationValid(equation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomDivider method, of class Equation.
     */
    @Test
    public void testGetRandomDivider() {
        System.out.println("getRandomDivider");
        int num = 0;
        Equation instance = new Equation();
        String expResult = "";
        String result = instance.getRandomDivider(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isStringContains method, of class Equation.
     */
    @Test
    public void testIsStringContains() {
        System.out.println("isStringContains");
        char[] chars = null;
        String str = "";
        Equation instance = new Equation();
        boolean expResult = false;
        boolean result = instance.isStringContains(chars, str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
