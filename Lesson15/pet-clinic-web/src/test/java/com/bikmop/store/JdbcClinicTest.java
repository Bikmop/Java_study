package com.bikmop.store;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Dog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcClinicTest {
    JdbcClinic clinic = null;

    @Before
    public void setClinic() {
        this.clinic = new JdbcClinic();
    }

    @After
    public void closeClinic() {
        clinic.close();
    }

    @Test
    public void testAdd_RemoveClient() throws Exception {
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertNull(clinic.getCurrentClient());

        clinic.addClient(new Client("_TestName", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertNotNull(clinic.getCurrentClient());
        assertEquals("_TestName", clinic.getCurrentClient().getFullName());

        clinic.removeCurrentClient();
        assertNull(clinic.getCurrentClient());
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertNull(clinic.getCurrentClient());
    }

    @Test
    public void testAddPets_RemoveCurrentClientWithAllPets() throws Exception {
        clinic.addClient(new Client("_TestName", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestCat").size());
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestDog").size());
        clinic.addPetToCurrentClient("1", "_TestCat");
        clinic.addPetToCurrentClient("2", "_TestDog");

        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "_TestCat").size());
        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "_TestDog").size());

        clinic.removeCurrentClient();
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestCat").size());
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestDog").size());
        assertNull(clinic.getCurrentClient());
    }

    @Test
    public void testGetClients() throws Exception {
        int initSize = clinic.getClients().size();
        Client client01 = new Client("_TestName01", "_TestId01");
        Client client02 = new Client("_TestName02", "_TestId02");
        client02.addPet(new Dog("_TestDog"));

        clinic.addClient(client01);
        clinic.addClient(client02);

        assertEquals(initSize + 2, clinic.getClients().size());

        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId01");
        clinic.removeCurrentClient();
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId02");
        clinic.removeCurrentClient();
    }

    @Test
    public void testRenameCurrentClient() throws Exception {
        clinic.addClient(new Client("_TestName", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "_TestNameNew");
        assertNull(clinic.getCurrentClient());
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertNotNull(clinic.getCurrentClient());
        assertEquals("_TestName", clinic.getCurrentClient().getFullName());

        clinic.renameCurrentClient("_TestNameNew");
        assertEquals("_TestNameNew", clinic.getCurrentClient().getFullName());
        clinic.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "_TestName");
        assertNull(clinic.getCurrentClient());
        clinic.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "_TestNameNew");
        assertNotNull(clinic.getCurrentClient());

        clinic.removeCurrentClient();
    }

    @Test
    public void testRemovePetFromCurrentClient_fillCurrentClientWithPets() throws Exception {
        clinic.addClient(new Client("_TestName", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestCat").size());
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestDog").size());
        clinic.addPetToCurrentClient("1", "_TestCat");
        clinic.addPetToCurrentClient("2", "_TestDog");

        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertEquals(2, clinic.getCurrentClient().getPets().size());
        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "_TestCat").size());
        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "_TestDog").size());
        clinic.removePetFromCurrentClient("_TestCat");
        assertEquals(1, clinic.getCurrentClient().getPets().size());
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "_TestCat").size());
        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "_TestDog").size());

        clinic.removeCurrentClient();
    }

    @Test
    public void testIsUniqueClientId() throws Exception {
        assertTrue(clinic.isUniqueClientId("_TestId"));
        clinic.addClient(new Client("_TestName", "_TestId"));
        assertFalse(clinic.isUniqueClientId("_TestId"));

        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        clinic.removeCurrentClient();

        assertTrue(clinic.isUniqueClientId("_TestId"));
    }

    @Test
    public void testFindClients() throws Exception {
        Client client01 = new Client("_TestName01", "_TestId01");
        Client client02 = new Client("_TestName02", "_TestId02");
        clinic.addClient(client01);
        clinic.addClient(client02);
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId01");
        clinic.addPetToCurrentClient("1", "Cat");
        clinic.addPetToCurrentClient("2", "Dog01");
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId02");
        clinic.addPetToCurrentClient("1", "Cat");
        clinic.addPetToCurrentClient("2", "Dog02");
        clinic.addPetToCurrentClient("3", "Fish");

        assertEquals(1, clinic.findClients(Client.SearchType.ID_FULL, "_TestId01").size());
        assertEquals(2, clinic.findClients(Client.SearchType.ID_PART, "_TestId").size());
        assertEquals(2, clinic.findClients(Client.SearchType.ID_PART, "TEST").size());
        assertEquals(1, clinic.findClients(Client.SearchType.NAME_FULL, "_TestName02").size());
        assertEquals(2, clinic.findClients(Client.SearchType.NAME_PART, "_TestName").size());
        assertEquals(2, clinic.findClients(Client.SearchType.NAME_PART, "estna").size());
        assertEquals(2, clinic.findClients(Client.SearchType.PETS_NAME, "Cat").size());
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "Dog").size());
        assertEquals(0, clinic.findClients(Client.SearchType.PETS_NAME, "dog01").size());
        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "Dog01").size());
        assertEquals(1, clinic.findClients(Client.SearchType.PETS_NAME, "Fish").size());

        clinic.removeCurrentClient();
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId01");
        clinic.removeCurrentClient();
    }
}