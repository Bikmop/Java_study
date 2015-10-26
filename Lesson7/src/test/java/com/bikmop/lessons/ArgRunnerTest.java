package com.bikmop.lessons;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgRunnerTest {
    private ArgRunner argRunner;
    private String[] args;

    private void setArgs(String... args) {
        this.args = args;
    }


    @Before
    public void setUp() throws Exception {
        this.argRunner = new ArgRunner();
    }


    // getResult():
    @Test(expected = IllegalArgumentException.class)
    public void testGetResultWrongParametersQuantity() throws Exception {
        setArgs("5", "7");
        argRunner.getResult(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetResultWrongIntegerParameter() throws Exception {
        setArgs("5.5", "/", "7");
        argRunner.getResult(args);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetResultWrongOperation() throws Exception {
        setArgs("5", "@", "7");
        argRunner.getResult(args);
    }

    @Test(expected = ArithmeticException.class)
    public void testGetResultDivisionByZero() throws Exception {
        setArgs("5", "/", "0");
        argRunner.getResult(args);
    }

    @Test
    public void testGetResultOk() throws Exception {
        setArgs("5", "*", "7");
        int result = argRunner.getResult(args);
        assertEquals(35, result);
    }

    @Test
    public void testGetResultOperationConversionMultiply() throws Exception {
        setArgs("5", "x", "7");
        int result = argRunner.getResult(args);
        assertEquals(35, result);
    }

    @Test
    public void testGetResultOperationConversionExponentiation() throws Exception {
        setArgs("5", "e", "2");
        int result = argRunner.getResult(args);
        assertEquals(25, result);
    }


    // showResult() - Just for coverage. Screen results do not tested
    @Test
    public void testShowResultCorrect() throws Exception {
        setArgs("5", "*", "7");
        argRunner.showResult(args);
    }

    @Test
    public void testShowResultException() throws Exception {
        setArgs("5", "7");
        argRunner.showResult(args);
    }


    // main()
    @Test
    public void testMain() throws Exception {
        setArgs("5", "*", "7");
        argRunner.main(args);
    }
}