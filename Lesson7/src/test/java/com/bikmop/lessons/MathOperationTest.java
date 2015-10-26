package com.bikmop.lessons;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathOperationTest {

    private MathOperation mo;

    public MathOperation getOperation(char symbol) throws Exception {
        return MathOperation.getOperationBySymbol(symbol);
    }

    @Test
    public void testGetOperationBySymbolAdd() throws Exception {
        mo = getOperation('+');
        assertEquals(MathOperation.ADD, mo);
    }

    @Test
    public void testGetOperationBySymbolSubtract() throws Exception {
        mo = getOperation('-');
        assertEquals(MathOperation.SUBTRACT, mo);
    }

    @Test
    public void testGetOperationBySymbolMultiply() throws Exception {
        mo = getOperation('*');
        assertEquals(MathOperation.MULTIPLY, mo);
    }

    @Test
    public void testGetOperationBySymbolDivide() throws Exception {
        mo = getOperation('/');
        assertEquals(MathOperation.DIVIDE, mo);
    }

    @Test
    public void testGetOperationBySymbolExponentiation() throws Exception {
        mo = getOperation('^');
        assertEquals(MathOperation.EXPONENTIATION, mo);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetOperationBySymbolUnknownSymbol() throws Exception {
        mo = getOperation('$');
    }

}