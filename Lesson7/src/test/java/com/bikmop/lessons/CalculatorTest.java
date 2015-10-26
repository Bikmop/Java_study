package com.bikmop.lessons;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calc;

    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }


    // Add
    @Test
    public void testAddPositive() throws Exception {
        calc.add(5, 6);
        assertEquals(11, calc.getResult());
    }

    @Test
    public void testAddNegative() throws Exception {
        calc.add(-5, -6);
        assertEquals(-11, calc.getResult());
    }

    @Test
    public void testAddDifferent01() throws Exception {
        calc.add(-5, 6);
        assertEquals(1, calc.getResult());
    }

    @Test
    public void testAddDifferent02() throws Exception {
        calc.add(5, -6);
        assertEquals(-1, calc.getResult());
    }

    @Test
    public void testAddDifferent03() throws Exception {
        calc.add(0, -6);
        assertEquals(-6, calc.getResult());
    }


    // Subtract
    @Test
    public void testSubtractPositive() throws Exception {
        calc.subtract(5, 6);
        assertEquals(-1, calc.getResult());
    }

    @Test
    public void testSubtractNegative() throws Exception {
        calc.subtract(-5, -6);
        assertEquals(1, calc.getResult());
    }

    @Test
    public void testSubtractDifferent01() throws Exception {
        calc.subtract(-5, 6);
        assertEquals(-11, calc.getResult());
    }

    @Test
    public void testSubtractDifferent02() throws Exception {
        calc.subtract(5, -6);
        assertEquals(11, calc.getResult());
    }

    @Test
    public void testSubtractDifferent03() throws Exception {
        calc.subtract(0, -6);
        assertEquals(6, calc.getResult());
    }


    // Multiply
    @Test
    public void testMultiplyPositive() throws Exception {
        calc.multiply(5, 6);
        assertEquals(30, calc.getResult());
    }

    @Test
    public void testMultiplyNegative() throws Exception {
        calc.multiply(-5, -6);
        assertEquals(30, calc.getResult());
    }

    @Test
    public void testMultiplyDifferent01() throws Exception {
        calc.multiply(-5, 6);
        assertEquals(-30, calc.getResult());
    }

    @Test
    public void testMultiplyDifferent02() throws Exception {
        calc.multiply(5, -6);
        assertEquals(-30, calc.getResult());
    }

    @Test
    public void testMultiplyDifferent03() throws Exception {
        calc.multiply(0, -6);
        assertEquals(0, calc.getResult());
    }


    // Divide
    @Test
    public void testDividePositive() throws Exception {
        calc.divide(600, 6);
        assertEquals(100, calc.getResult());
    }

    @Test
    public void testDivideNegative() throws Exception {
        calc.divide(-17, -6);
        assertEquals(2, calc.getResult());
    }

    @Test
    public void testDivideDifferent01() throws Exception {
        calc.divide(-5, 6);
        assertEquals(0, calc.getResult());
    }

    @Test
    public void testDivideDifferent02() throws Exception {
        calc.divide(20, -6);
        assertEquals(-3, calc.getResult());
    }

    @Test
    public void testDivideDifferent03() throws Exception {
        calc.divide(0, -6);
        assertEquals(0, calc.getResult());
    }

    @Test(expected=ArithmeticException.class)
    public void testDivideByZeroException() throws Exception {
        calc.divide(5, 0);
    }


    // Exponentiation
    @Test
    public void testExponentiationPositive() throws Exception {
        calc.exponentiation(6, 2);
        assertEquals(36, calc.getResult());
    }

    @Test
    public void testExponentiationNegative() throws Exception {
        calc.exponentiation(-17, -6);
        assertEquals(0, calc.getResult());
    }

    @Test
    public void testExponentiationDifferent() throws Exception {
        calc.exponentiation(-5, 3);
        assertEquals(-125, calc.getResult());
    }

    @Test
    public void testExponentiationToZero() throws Exception {
        calc.exponentiation(5, 0);
        assertEquals(1, calc.getResult());
    }

    @Test
    public void testExponentiationZeroToZero() throws Exception {
        calc.exponentiation(0, 0);
        assertEquals(1, calc.getResult());
    }


    // Clear
    @Test
    public void testClearResult() throws Exception {
        calc.add(0, 5);
        calc.clearResult();
        assertEquals(0, calc.getResult());
    }


    // Calculate
    @Test
    public void testCalculateADD() throws Exception {
        calc.calculate(5, MathOperation.ADD, 6);
        assertEquals(11, calc.getResult());
    }

    @Test
    public void testCalculateSUBTRACT() throws Exception {
        calc.calculate(5, MathOperation.SUBTRACT, 6);
        assertEquals(-1, calc.getResult());
    }

    @Test
    public void testCalculateMULTIPLY() throws Exception {
        calc.calculate(5, MathOperation.MULTIPLY, 6);
        assertEquals(30, calc.getResult());
    }

    @Test
    public void testCalculateDivide() throws Exception {
        calc.calculate(15, MathOperation.DIVIDE, 6);
        assertEquals(2, calc.getResult());
    }

    @Test
    public void testCalculateExponentiation() throws Exception {
        calc.calculate(2, MathOperation.EXPONENTIATION, 2);
        assertEquals(4, calc.getResult());
    }



}