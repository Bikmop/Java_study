package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class BirdTest {

    @Test
    public void testGetPetType() throws Exception {
        Bird bird = new Bird("Birdie");
        assertEquals("Bird", bird.getStringPetType());
    }

    @Test
    public void testRuGetPetType() throws Exception {
        Bird bird = new Bird("Birdie");
        assertEquals("Птица", bird.getRuStringPetType());
    }

}