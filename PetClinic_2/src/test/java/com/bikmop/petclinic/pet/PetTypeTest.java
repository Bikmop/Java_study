package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetTypeTest {

    @Test
    public void testGetPetTypeByString1() throws Exception {
        assertEquals(PetType.CAT, PetType.getPetTypeByString("1"));
    }

    @Test
    public void testGetPetTypeByString2() throws Exception {
        assertEquals(PetType.DOG, PetType.getPetTypeByString("2"));
    }

    @Test
    public void testGetPetTypeByString3() throws Exception {
        assertEquals(PetType.FISH, PetType.getPetTypeByString("3"));
    }

    @Test
    public void testGetPetTypeByString4() throws Exception {
        assertEquals(PetType.BIRD, PetType.getPetTypeByString("4"));
    }

    @Test
    public void testGetPetTypeByString5() throws Exception {
        assertEquals(PetType.REPTILE, PetType.getPetTypeByString("5"));
    }

    @Test
    public void testGetPetTypeByString6() throws Exception {
        assertEquals(PetType.RODENT, PetType.getPetTypeByString("6"));
    }

    @Test
    public void testGetPetTypeByStringAnother() throws Exception {
        assertEquals(PetType.SOME_PET, PetType.getPetTypeByString(""));
    }

}
