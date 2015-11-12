package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.client.Client.SearchType;
import com.bikmop.petclinic.pet.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicTest {
    private Clinic clinic;


    //Additional methods for test preparing:

    private void createClinic(final int clientsNumber) {
        clinic = new Clinic(clientsNumber);
    }

    private void addClientById(String clientId) {
        addClient(new Client(clientId));
    }

    private Client[] getClients() {
        return clinic.getClients();
    }

    private void addClient(Client client) {
        clinic.addClient(client);
    }

    private void removeClientById(String id) {
        selectClient(SearchType.ID_FULL, id);
        removeClient();
    }

    private void fillClients() {
        createClinic(5);
        // Anna Ivanova    Id: XX 33335789    Pets: Bird 'Kesha',  Rodent 'Mickey',  Reptile 'Python',  Pet 'Snail'
        addClient(createAnna());
        // Ivan Petrov    Id: XY 01234567    Pets: no pets
        addClient(createIvan());
        // Petr Sidorov    Id: XY 89012345    Pets: Cat 'Masha',  Cat 'Python',  Dog 'Palkan'
        addClient(createPetr());
    }

    private Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

    private Client createIvan() {
        Client ivan = new Client("XY 01234567");
        ivan.setFullName("Ivan Petrov");

        return ivan;
    }

    private Client createPetr() {
        Client petr = new Client("XY 89012345");
        petr.setFullName("Petr Sidorov");
        petr.addPet(new Cat("Masha"));
        petr.addPet(new Cat("Python"));
        petr.addPet(new Dog("Palkan"));

        return petr;
    }

    private Client getCurrentClient() {
        return clinic.getCurrentClient();
    }

    private Client[] findClients(SearchType type, String toSearch) {
        return clinic.findClients(type, toSearch);
    }

    private void selectClient(SearchType idFull, String selectString) {
        clinic.selectFirstMatchingClient(idFull, selectString);
    }

    private boolean isUniqueClientId(String clientId) {
        return clinic.isUniqueClientId(clientId);
    }

    private void removeClient() {
        clinic.removeCurrentClient();
    }

    private void renameClient() {
        clinic.renameCurrentClient("Anna Sidorova");
    }



// Tests:

    //AddClient
    @Test
    public void testAddClient() throws Exception {
        final int maxClients = 5;

        createClinic(maxClients);
        Client[] clients = getClients();
        assertNull(clients[0]);
        addClientById("SomeId");
        assertNotNull(clients[0]);
        assertNull(clients[1]);
    }

    @Test
    public void testAddClientMakeFull() throws Exception {
        final int maxClients = 3;

        createClinic(maxClients);
        Client[] clients = getClients();
        assertNull(clients[0]);
        assertNull(clients[1]);
        assertNull(clients[2]);
        addClientById("SomeId01");
        addClientById("SomeId02");
        addClientById("SomeId03");
        assertNotNull(clients[0]);
        assertNotNull(clients[1]);
        assertNotNull(clients[2]);
    }

    @Test(expected = FullClientsArrayException.class)
    public void testAddClientToFull() throws Exception {
        final int maxClients = 3;

        createClinic(maxClients);
        addClientById("SomeId01");
        addClientById("SomeId02");
        addClientById("SomeId03");
        addClientById("SomeId04");
    }

    @Test
    public void testAddClientToMiddleOfArrayAfterRemoving() throws Exception {
        final int maxClients = 5;

        createClinic(maxClients);
        Client[] clients = getClients();
        addClientById("SomeId01");
        addClientById("SomeId02");
        addClientById("SomeId03");
        //Remove from middle
        removeClientById("SomeId02");
        //Check for null/not null
        assertNotNull(clients[0]);
        assertNull(clients[1]);
        assertNotNull(clients[2]);
        assertNull(clients[3]);
        //Add and check
        addClientById("SomeId04");
        assertNotNull(clients[1]);
        assertNull(clients[3]);
        assertEquals("SomeId04", clients[1].getId());
    }


    //Clients search

    /*  Anna Ivanova    Id: XX 33335789    Pets: Bird 'Kesha',  Rodent 'Mickey',  Reptile 'Python',  Pet 'Snail'
        Ivan Petrov    Id: XY 01234567    Pets: no pets
        Petr Sidorov    Id: XY 89012345    Pets: Cat 'Masha',  Cat 'Python',  Dog 'Palkan'
    */

    //Client.SearchType.ID_FULL
    @Test
    public void testFindClientsFullId() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.ID_FULL, "XY 01234567");
        assertNotNull(clients[0]);
        assertNull(clients[1]);
    }

    @Test
    public void testFindClientsFullIdNotFound() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.ID_FULL, "XY 00000000");
        assertNull(clients[0]);
    }

    //Client.SearchType.ID_PART
    @Test
    public void testFindClientsPartId01() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.ID_PART, "XY");
        assertNotNull(clients[0]);
        assertNotNull(clients[1]);
        assertNull(clients[2]);
    }

    @Test
    public void testFindClientsPartId02() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.ID_PART, "xx");
        assertNotNull(clients[0]);
        assertNull(clients[1]);
    }

    @Test
    public void testFindClientsPartId03() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.ID_PART, "5");
        assertNotNull(clients[0]);
        assertNotNull(clients[1]);
        assertNotNull(clients[2]);
        assertNull(clients[3]);
    }

    @Test
    public void testFindClientsPartIdNotFound() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.ID_PART, "XYZ");
        assertNull(clients[0]);
    }

    //Client.SearchType.NAME_FULL
    @Test
    public void testFindClientsFullName() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.NAME_FULL, "Ivan Petrov");
        assertNotNull(clients[0]);
        assertNull(clients[1]);
    }

    @Test
    public void testFindClientsFullNameNotFound() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.NAME_FULL, "Ivan");
        assertNull(clients[0]);
    }

    //Client.SearchType.NAME_PART
    @Test
    public void testFindClientsPartName01() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.NAME_PART, "ivan petrov");
        assertNotNull(clients[0]);
        assertNull(clients[1]);
    }

    @Test
    public void testFindClientsPartName02() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.NAME_PART, "ivan");
        assertNotNull(clients[0]);
        assertNotNull(clients[1]);
        assertNull(clients[2]);
    }

    @Test
    public void testFindClientsPartName03() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.NAME_PART, "ov");
        assertNotNull(clients[0]);
        assertNotNull(clients[1]);
        assertNotNull(clients[2]);
        assertNull(clients[3]);
    }

    @Test
    public void testFindClientsPartNameNotFound() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.NAME_PART, "Ivan  ");
        assertNull(clients[0]);
    }

    //Client.SearchType.PETS_NAME
    @Test
    public void testFindClientsNameOfPet01() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.PETS_NAME, "Kesha");
        assertNotNull(clients[0]);
        assertNull(clients[1]);
    }

    @Test
    public void testFindClientsNameOfPet02() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.PETS_NAME, "Python");
        assertNotNull(clients[0]);
        assertNotNull(clients[1]);
        assertNull(clients[2]);
    }

    @Test
    public void testFindClientsNameOfPetNotFound() throws Exception {
        fillClients();
        Client[] clients = findClients(SearchType.PETS_NAME, "kesha");
        assertNull(clients[0]);
    }


    //Select client

    /*  Anna Ivanova    Id: XX 33335789    Pets: Bird 'Kesha',  Rodent 'Mickey',  Reptile 'Python',  Pet 'Snail'
        Ivan Petrov    Id: XY 01234567    Pets: no pets
        Petr Sidorov    Id: XY 89012345    Pets: Cat 'Masha',  Cat 'Python',  Dog 'Palkan'
    */

    //Client.SearchType.ID_FULL
    @Test
    public void selectFirstMatchingClientFullId() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.ID_FULL, "XY 01234567");
        assertEquals(ivan, getCurrentClient());
    }

    @Test
    public void selectFirstMatchingClientFullIdNotFound() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.ID_FULL, "XY 00000000");
        assertNull(getCurrentClient());
    }

    //Client.SearchType.ID_PART
    @Test
    public void selectFirstMatchingClientPartId() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.ID_PART, "XY");
        assertEquals(petr, getCurrentClient());
    }

    @Test
    public void selectFirstMatchingClientPartIdNotFound() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.ID_PART, "XY");
        assertNotNull(getCurrentClient());
        selectClient(SearchType.ID_PART, "XYZ");
        assertNull(getCurrentClient());
    }

    //Client.SearchType.NAME_FULL
    @Test
    public void selectFirstMatchingClientFullName() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.NAME_FULL, "Anna Ivanova");
        assertEquals(anna, getCurrentClient());
    }

    //Client.SearchType.NAME_PART
    @Test
    public void selectFirstMatchingClientPartName01() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.NAME_PART, "Ivan");
        assertEquals(anna, getCurrentClient()); //Ivanova
    }

    //Client.SearchType.PETS_NAME
    @Test
    public void selectFirstMatchingClientNameOfPet02() throws Exception {
        createClinic(5);
        Client anna = createAnna();
        Client petr = createPetr();
        Client ivan = createIvan();
        addClient(anna);
        addClient(petr);
        addClient(ivan);
        selectClient(SearchType.PETS_NAME, "Python");
        assertEquals(anna, getCurrentClient());
    }


    //Remove client
    @Test
    public void testRemoveCurrentClient() throws Exception {
        fillClients();
        selectClient(SearchType.NAME_FULL, "Anna Ivanova");
        removeClient();
        assertNull(getCurrentClient());
        Client[] clients = findClients(SearchType.NAME_FULL, "Anna Ivanova");
        assertNull(clients[0]);
    }


    //Rename client
    @Test
    public void testRenameCurrentClient() throws Exception {
        fillClients();
        selectClient(SearchType.NAME_FULL, "Anna Ivanova");
        renameClient();
        Client[] clients = findClients(SearchType.NAME_FULL, "Anna Ivanova");
        assertNull(clients[0]);
        clients = findClients(SearchType.NAME_FULL, "Anna Sidorova");
        assertNotNull(clients[0]);
    }


    //IsUniqueId client
    @Test
    public void testIsUniqueClientIdTrue() throws Exception {
        fillClients();
        assertTrue(isUniqueClientId("XX 00000000"));
    }

    @Test
    public void testIsUniqueClientIdFalse() throws Exception {
        fillClients();
        assertFalse(isUniqueClientId("XX 33335789"));
    }

}