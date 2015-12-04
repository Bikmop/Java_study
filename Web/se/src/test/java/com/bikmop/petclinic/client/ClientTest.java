package com.bikmop.petclinic.client;

import com.bikmop.petclinic.client.Client.SearchType;
import com.bikmop.petclinic.pet.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class ClientTest {
    private Client client;


    // Additional methods for tests:

    private void createClient(String id) {
        client = new Client(id);
    }

    private void addPet(Pet pet) {
        client.addPet(pet);
    }

    private void setClientName(String name) {
        client.setFullName(name);
    }

    private void renamePet(String oldName, String newName) {
        client.renamePet(oldName, newName);
    }

    private void removePetByName(String petName) {
        client.removePetByName(petName);
    }

    private String getId() {
        return client.getId();
    }

    private String getFullName() {
        return client.getFullName();
    }

    private List<Pet> getPets() {
        return client.getPets();
    }

    private boolean hasPetWithName(String petName) {
        return client.hasPetWithName(petName);
    }

    private boolean hasInId(String id) {
        return client.hasInId(id);
    }

    private boolean hasInName(String searchString) {
        return client.hasInName(searchString);
    }

    private boolean hasIn(SearchType type, String search) {
        return client.hasIn(type, search);
    }



    //Tests:

    // GetId
    @Test
    public void testGetIdOk() throws Exception {
        createClient("0123456789");
        assertEquals("0123456789", getId());
    }

    @Test
    public void testGetIdWrong() throws Exception {
        createClient("0123456789");
        assertNotSame("012345678", getId());
    }


    // Set/GetFullName
    @Test
    public void testSetGetFullNameOk() throws Exception {
        createClient("XX");
        setClientName("Client");
        assertEquals("Client", getFullName());
    }

    @Test
    public void testSetGetFullNameWrong() throws Exception {
        createClient("XX");
        setClientName("Client");
        assertNotSame("client", getFullName());
    }


    // AddPet
    @Test
    public void testAddPet() throws Exception {
        createClient("Id");
        assertEquals(0, getPets().size());
        addPet(new Reptile("Snaky"));
        assertEquals(1, getPets().size());
        assertTrue(hasPetWithName("Snaky"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddPetAlreadyHasPet() throws Exception {
        createClient("Id");
        addPet(new Reptile("Snaky"));
        addPet(new Reptile("Snaky"));
    }

    @Test
    public void testAddPetAlreadyHasPetCheckSize() throws Exception {
        createClient("Id");
        assertEquals(0, getPets().size());
        addPet(new Reptile("Snaky"));

        try {
            addPet(new Reptile("Snaky"));
        } catch (IllegalArgumentException iae) {
            //Ignore
        }

        assertEquals(1, getPets().size());
    }


    // HasInId
    @Test
    public void testHasInIdTrue01() throws Exception {
        createClient("XX 123456789");
        assertTrue(hasInId("1234"));
    }

    @Test
    public void testHasInIdTrue02() throws Exception {
        createClient("XX 123456789");
        assertTrue(hasInId("XX 1234"));
    }

    @Test
    public void testHasInIdFalse01() throws Exception {
        createClient("XX 123456789");
        assertFalse(hasInId("12341"));
    }

    @Test
    public void testHasInIdFalse02() throws Exception {
        createClient("XX 123456789");
        assertFalse(hasInId("XX1234"));
    }


    //HasInName
    @Test
    public void testHasInNameTrue01() throws Exception {
        createClient("XX");
        setClientName("FirstName LastName");
        assertTrue(hasInName("name last"));
    }

    @Test
    public void testHasInNameTrue02() throws Exception {
        createClient("XX");
        setClientName("FirstName LastName");
        assertTrue(hasInName("LAsT"));
    }

    @Test
    public void testHasInNameFalse() throws Exception {
        createClient("XX");
        setClientName("FirstName LastName");
        assertFalse(hasInName("lstName"));
    }


    //ToString
    @Test
    public void testToString() throws Exception {
        final String expected = "FirstName LastName    Id: XX 00000000    Pets: Reptile 'Snaky',  Cat 'Kitty'";

        createClient("XX 00000000");
        setClientName("FirstName LastName");
        addPet(new Reptile("Snaky"));
        addPet(new Cat("Kitty"));
        assertEquals(expected, client.toString());
    }

    @Test
    public void testToStringNoPets() throws Exception {
        final String expected = "FirstName LastName    Id: XX 00000000    Pets: no pets";

        createClient("XX 00000000");
        setClientName("FirstName LastName");
        assertEquals(expected, client.toString());
    }


    //GetSearchTypeByString
    @Test
    public void testGetSearchTypeByStringPartId() throws Exception {
        assertEquals(SearchType.ID_PART, SearchType.getSearchTypeByString("1"));
    }

    @Test
    public void testGetSearchTypeByStringFullId() throws Exception {
        assertEquals(SearchType.ID_FULL, SearchType.getSearchTypeByString("2"));
    }

    @Test
    public void testGetSearchTypeByStringPartName() throws Exception {
        assertEquals(SearchType.NAME_PART, SearchType.getSearchTypeByString("3"));
    }

    @Test
    public void testGetSearchTypeByStringFullName() throws Exception {
        assertEquals(SearchType.NAME_FULL, SearchType.getSearchTypeByString("4"));
    }

    @Test
    public void testGetSearchTypeByStringPetName() throws Exception {
        assertEquals(SearchType.PETS_NAME, SearchType.getSearchTypeByString("5"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetSearchTypeByStringDefault() throws Exception {
        SearchType.getSearchTypeByString(" ");
    }


    //HasPetWithName
    @Test
    public void testHasPetWithNameTrue01() throws Exception {
        createClient("XX");
        addPet(new Cat("Kitty"));
        addPet(new Rodent("Mouse"));
        assertTrue(hasPetWithName("Kitty"));
    }

    @Test
    public void testHasPetWithNameTrue02() throws Exception {
        createClient("XX");
        addPet(new Cat("Kitty"));
        addPet(new Rodent("Mouse"));
        assertTrue(hasPetWithName("Mouse"));
    }

    @Test
    public void testHasPetWithNameFalse() throws Exception {
        createClient("XX");
        addPet(new Cat("Kitty"));
        addPet(new Rodent("Mouse"));
        assertFalse(hasPetWithName("Kitt"));
    }


    //HasIn
    @Test
    public void testHasInPartId() throws Exception {
        createClient("XX 0000000");
        assertTrue(hasIn(SearchType.ID_PART, "xx"));
    }

    @Test
    public void testHasInPartIdFalse() throws Exception {
        createClient("XX 0000000");
        assertFalse(hasIn(SearchType.ID_PART, "XY"));
    }

    @Test
    public void testHasInFullId() throws Exception {
        createClient("XX 0000000");
        assertTrue(hasIn(SearchType.ID_FULL, "XX 0000000"));
    }

    @Test
    public void testHasInFullIdFalse() throws Exception {
        createClient("XX 0000000");
        assertFalse(hasIn(SearchType.ID_FULL, "xx 0000000"));
    }

    @Test
    public void testHasInPartName() throws Exception {
        createClient("XX 0000000");
        setClientName("FirstName LastName");
        assertTrue(hasIn(SearchType.NAME_PART, "name LAsT"));
    }

    @Test
    public void testHasInPartNameFalse() throws Exception {
        createClient("XX 0000000");
        setClientName("FirstName LastName");
        assertFalse(hasIn(SearchType.NAME_PART, "last "));
    }

    @Test
    public void testHasInFullName() throws Exception {
        createClient("XX 0000000");
        setClientName("FirstName LastName");
        assertTrue(hasIn(SearchType.NAME_FULL, "FirstName LastName"));
    }

    @Test
    public void testHasInFullNameFalse() throws Exception {
        createClient("XX 0000000");
        setClientName("FirstName LastName");
        assertFalse(hasIn(SearchType.NAME_FULL, "firstname lastname"));
    }

    @Test
    public void testHasInPetName() throws Exception {
        createClient("XX 0000000");
        setClientName("FirstName LastName");
        addPet(new Cat("Kitty"));
        assertTrue(hasIn(SearchType.PETS_NAME, "Kitty"));
    }

    @Test
    public void testHasInPetNameFalse() throws Exception {
        createClient("XX 0000000");
        setClientName("FirstName LastName");
        addPet(new Cat("Kitty"));
        assertFalse(hasIn(SearchType.PETS_NAME, "kitty"));
    }


    //RenamePet
    @Test
    public void testRenamePet() throws Exception {
        createClient("XX 0000000");
        addPet(new Cat("Kitty"));
        assertTrue(hasPetWithName("Kitty"));
        assertFalse(hasPetWithName("Cat"));
        renamePet("Kitty", "Cat");
        assertFalse(hasPetWithName("Kitty"));
        assertTrue(hasPetWithName("Cat"));
    }

    @Test
    public void testRenamePetNoSuchPet() throws Exception {
        createClient("XX");
        addPet(new Cat("Tom"));
        assertFalse(hasPetWithName("Kitty"));
        renamePet("Kitty", "Cat");
    }

    @Test
    public void testRenamePetEmptyPetsList() throws Exception {
        createClient("XX");
        assertFalse(hasPetWithName("Kitty"));
        renamePet("Kitty", "Cat");
    }


    //RemovePetByName
    @Test
    public void testRemovePetByName() throws Exception {
        createClient("XX");
        addPet(new Cat("Tom"));
        addPet(new Dog("Pit"));
        addPet(new Rodent("Jerry"));
        assertTrue(hasPetWithName("Pit"));
        assertEquals(3, getPets().size());
        removePetByName("Pit");
        assertFalse(hasPetWithName("Pit"));
        assertEquals(2, getPets().size());
    }

    @Test
    public void testRemovePetByNameRemoveAll() throws Exception {
        createClient("XX");
        addPet(new Cat("Tom"));
        addPet(new Dog("Pit"));
        addPet(new Rodent("Jerry"));
        assertTrue(hasPetWithName("Tom"));
        assertTrue(hasPetWithName("Pit"));
        assertTrue(hasPetWithName("Jerry"));
        assertEquals(3, getPets().size());
        removePetByName("Tom");
        removePetByName("Pit");
        removePetByName("Jerry");
        assertFalse(hasPetWithName("Tom"));
        assertFalse(hasPetWithName("Pit"));
        assertFalse(hasPetWithName("Jerry"));
        assertEquals(0, getPets().size());
    }

    @Test
    public void testRemovePetByNameWrongName() throws Exception {
        createClient("XX");
        addPet(new Cat("Tom"));
        assertEquals(1, getPets().size());
        removePetByName("Tim");
        assertEquals(1, getPets().size());
    }

}