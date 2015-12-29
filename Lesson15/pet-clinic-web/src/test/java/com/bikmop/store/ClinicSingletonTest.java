package com.bikmop.store;

import com.bikmop.petclinic.client.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicSingletonTest {

    @Test
    public void testGetInstance() throws Exception {
        ClinicSingleton instance1 = ClinicSingleton.getInstance();
        instance1.addClient(new Client("Test", "00"));

        ClinicSingleton instance2 = ClinicSingleton.getInstance();
        assertEquals(1, instance2.findClients(Client.SearchType.ID_FULL, "00").size());
        assertTrue(instance1 == instance2);

        instance1.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "Test");
        instance1.removeCurrentClient();
        instance1.close();
    }
}