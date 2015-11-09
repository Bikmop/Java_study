package com.bikmop.petclinic.client;

import com.bikmop.petclinic.pet.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class ClientTest {
/*
    private Client client;

    // Additional methods for tests
    private void createClient(String id) {
        client = new Client(id);
    }

    private void addPet(Pet pet) {
        client.addPet(pet);
    }

    private void setPets(List<Pet> pets) {
        client.setPets(pets);
    }

    private void setClientName(String name) {
        client.setFullName(name);
    }

    private List<Pet> createTestPetList01() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(new Cat("Kitty"));
        pets.add(new Dog("Doggy"));
        pets.add(new Rodent("Mouse"));
        pets.add(new Bird("Birdie"));
        return pets;
    }

    private List<Pet> createTestPetList02() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(new Cat("Petty"));
        pets.add(new Dog("Petty"));
        pets.add(new Rodent("Petunia"));
        pets.add(new Bird("Peter"));
        return pets;
    }

    private void removePetByName(String petName) {
        client.removePetByName(petName);
    }

    private void renamePet(String oldName, String newName) {
        client.renamePet(oldName, newName);
    }

    private void renamePet(Pet pet, String newName) {
        client.renamePet(pet, newName);
    }

    private List<Pet> findPetsByFullName(String petsName) {
        return client.findPetsByFullName(petsName);
    }

    private List<Pet> findPetsByPartName(String partOfName) {
        return client.findPetsByPartName(partOfName);
    }



    // IsInID
    @Test
    public void testHasInIdTrue01() throws Exception {
        createClient("XX 123456789");
        assertTrue(client.hasInId("1234"));
    }

    @Test
    public void testHasInIdTrue02() throws Exception {
        createClient("XX 123456789");
        assertTrue(client.hasInId("XX 1234"));
    }

    @Test
    public void testHasInIdFalse01() throws Exception {
        createClient("XX 123456789");
        assertFalse(client.hasInId("12341"));
    }

    @Test
    public void testHasInIdFalse02() throws Exception {
        createClient("XX 123456789");
        assertFalse(client.hasInId("XX1234"));
    }


    //IsInName
    @Test
    public void testHasInNameTrue01() throws Exception {
        createClient("XX");
        setClientName("FirstName LastName");
        assertTrue(client.hasInName("name last"));
    }

    @Test
    public void testHasInNameTrue02() throws Exception {
        createClient("XX");
        setClientName("FirstName LastName");
        assertTrue(client.hasInName("LAsT"));
    }

    @Test
    public void testHasInNameFalse() throws Exception {
        createClient("XX");
        setClientName("FirstName LastName");
        assertFalse(client.hasInName("lstName"));
    }


    //hasPetWithName
*/
/*    @Test
    public void testHasPetWithNameTrue01() throws Exception {
        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertTrue(client.hasPetWithName("Kitty"));
    }

    @Test
    public void testHasPetWithNameTrue02() throws Exception {
        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertTrue(client.hasPetWithName("MoUsE"));
    }

    @Test
    public void testHasPetWithNameTrue03() throws Exception {
        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertTrue(client.hasPetWithName("irdie"));
    }

    @Test
    public void testHasPetWithFalse() throws Exception {
        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertFalse(client.hasPetWithName("Reptile"));
    }*//*



    // Set/GetPets
    @Test
    public void testSetGetPetsSame() throws Exception {
        List<Pet> pets = createTestPetList01();
        createClient("XX");
        setPets(pets);
        assertEquals(pets, client.getPets());
    }

    @Test
    public void testSetGetPetsDifferent() throws Exception {
        List<Pet> pets = createTestPetList01();
        createClient("XX");
        assertNotSame(pets, client.getPets());
    }


    // Set/GetFullName
    @Test
    public void testSetGetFullNameOk() throws Exception {
        createClient("XX");
        setClientName("Client");
        assertEquals("Client", client.getFullName());
    }

    @Test
    public void testSetGetFullNameWrong() throws Exception {
        createClient("XX");
        setClientName("Client");
        assertNotSame("client", client.getFullName());
    }


    // GetId
    @Test
    public void testGetIdOk() throws Exception {
        createClient("0123456789");
        assertEquals("0123456789", client.getId());
    }

    @Test
    public void testGetIdWrong() throws Exception {
        createClient("0123456789");
        assertNotSame("012345678", client.getId());
    }


    // AddPet
*/
/*    @Test
    public void testAddPetNotEmptyList() throws Exception {
        final int SIZE_BEFORE = 4;
        final int SIZE_AFTER = 5;

        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertFalse(client.hasPetWithName("Snaky"));
        assertEquals(SIZE_BEFORE, client.getPets().size());
        addPet(new Reptile("Snaky"));
        assertTrue(client.hasPetWithName("Snaky"));
        assertEquals(SIZE_AFTER, client.getPets().size());
    }*//*


    @Test
    public void testAddPetEmptyList() throws Exception {
        final int SIZE_BEFORE = 0;
        final int SIZE_AFTER_FIRST = 1;
        final int SIZE_AFTER_SECOND = 3;

        createClient("XX");
        assertFalse(client.hasPetWithName("Snaky"));
        assertEquals(SIZE_BEFORE, client.getPets().size());
        addPet(new Reptile("Snaky"));
        assertTrue(client.hasPetWithName("Snaky"));
        assertEquals(SIZE_AFTER_FIRST, client.getPets().size());
        addPet(new Fish("Fishy"));
        addPet(new SomePet("Other pet"));
        assertEquals(SIZE_AFTER_SECOND, client.getPets().size());
    }


    // RemovePetByName
*/
/*    @Test
    public void testRemovePetByName() throws Exception {
        final int SIZE_BEFORE = 4;
        final int SIZE_AFTER = 3;

        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertTrue(client.hasPetWithName("Doggy"));
        assertEquals(SIZE_BEFORE, client.getPets().size());
        removePetByName("Doggy");
        assertFalse(client.hasPetWithName("Doggy"));
        assertEquals(SIZE_AFTER, client.getPets().size());

    }*//*


*/
/*    @Test
    public void testRemovePetByNameRemoveAll() throws Exception {
        final int SIZE_BEFORE = 4;
        final int SIZE_AFTER = 0;

        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertTrue(client.hasPetWithName("Kitty"));
        assertTrue(client.hasPetWithName("Doggy"));
        assertTrue(client.hasPetWithName("Mouse"));
        assertTrue(client.hasPetWithName("Birdie"));
        assertEquals(SIZE_BEFORE, client.getPets().size());
        removePetByName("Kitty");
        removePetByName("Doggy");
        removePetByName("Mouse");
        removePetByName("Birdie");
        assertFalse(client.hasPetWithName("Kitty"));
        assertFalse(client.hasPetWithName("Doggy"));
        assertFalse(client.hasPetWithName("Mouse"));
        assertFalse(client.hasPetWithName("Birdie"));
        assertEquals(SIZE_AFTER, client.getPets().size());

    }*//*


*/
/*    @Test
    public void testRemovePetByNameWrongNameWithoutException() throws Exception {
        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        removePetByName("Kit");
    }*//*



    //RenamePet
*/
/*    @Test
    public void testRenamePet() throws Exception {
        List<Pet> pets = createTestPetList01();       // "Kitty", "Doggy", "Mouse", "Birdie"
        createClient("XX", pets);
        assertTrue(client.hasPetWithName("Kitty"));
        assertFalse(client.hasPetWithName("Cat"));
        renamePet("Kitty", "Cat");
        assertFalse(client.hasPetWithName("Kitty"));
        assertTrue(client.hasPetWithName("Cat"));
    }*//*


    @Test
    public void testRenamePetEmptyList() throws Exception {
        createClient("XX");
        assertFalse(client.hasPetWithName("Kitty"));
        renamePet("Kitty", "Cat");
    }

    @Test
    public void testRenamePetVersionForPetAndName() throws Exception {
        createClient("XX");
        assertFalse(client.hasPetWithName("Kitty"));
        Pet cat = new Cat("Kitty");
        addPet(cat);
        assertTrue(client.hasPetWithName("Kitty"));
        renamePet(cat, "Cat");
        assertFalse(client.hasPetWithName("Kitty"));
        assertTrue(client.hasPetWithName("Cat"));
    }

    //FindPetsByFullName
*/
/*    @Test
    public void testFindPetsByFullName01() throws Exception {
        List<Pet> pets = createTestPetList02();       // "Petty", "Petty", "Petunia", "Peter"
        createClient("XX", pets);
        List<Pet> foundPets = findPetsByFullName("Petty");
        assertEquals(2, foundPets.size());
    }*//*


*/
/*
    @Test
    public void testFindPetsByFullName02() throws Exception {
        List<Pet> pets = createTestPetList02();       // "Petty", "Petty", "Petunia", "Peter"
        createClient("XX", pets);
        List<Pet> foundPets = findPetsByFullName("petty");
        assertEquals(0, foundPets.size());
    }

    @Test
    public void testFindPetsByFullName03() throws Exception {
        List<Pet> pets = createTestPetList02();       // "Petty", "Petty", "Petunia", "Peter"
        createClient("XX", pets);
        List<Pet> foundPets = findPetsByFullName("pet");
        assertEquals(0, foundPets.size());
    }



    //FindPetsByPartName
    @Test
    public void testFindPetsByPartName01() throws Exception {
        List<Pet> pets = createTestPetList02();       // "Petty", "Petty", "Petunia", "Peter"
        createClient("XX", pets);
        List<Pet> foundPets = findPetsByPartName("petty");
        assertEquals(2, foundPets.size());
    }

    @Test
    public void testFindPetsByPartName02() throws Exception {
        List<Pet> pets = createTestPetList02();       // "Petty", "Petty", "Petunia", "Peter"
        createClient("XX", pets);
        List<Pet> foundPets = findPetsByPartName("pet");
        assertEquals(4, foundPets.size());
    }

    @Test
    public void testFindPetsByPartName03() throws Exception {
        List<Pet> pets = createTestPetList02();       // "Petty", "Petty", "Petunia", "Peter"
        createClient("XX", pets);
        List<Pet> foundPets = findPetsByPartName("tun");
        assertEquals(1, foundPets.size());
        addPet(new Cat("Tunny"));
        foundPets = findPetsByPartName("tun");
        assertEquals(2, foundPets.size());
    }
*//*


*/


}