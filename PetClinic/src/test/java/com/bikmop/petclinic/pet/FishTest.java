package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class FishTest {

    @Test
    public void testGetPetType() throws Exception {
        Fish fish = new Fish("Fishy");
        assertEquals("Fish", fish.getPetType());
    }

}