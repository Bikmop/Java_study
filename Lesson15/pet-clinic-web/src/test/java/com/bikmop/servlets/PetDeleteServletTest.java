package com.bikmop.servlets;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Bird;
import com.bikmop.petclinic.pet.Reptile;
import com.bikmop.petclinic.pet.Rodent;
import com.bikmop.petclinic.pet.SomePet;
import com.bikmop.store.ClinicSingleton;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

public class PetDeleteServletTest extends Mockito {
    final ClinicSingleton clinic = ClinicSingleton.getInstance();

    @Test
    public void testRemovePet() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertFalse(clinic.getCurrentClient().hasPetWithName("_TestBird"));
        clinic.addPetToCurrentClient("4", "_TestBird");
        clinic.addPetToCurrentClient("6", "_TestRodent");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("_TestBird");

        assertTrue(clinic.getCurrentClient().hasPetWithName("_TestBird"));

        PetDeleteServlet petDeleteServlet = new PetDeleteServlet();
        petDeleteServlet.doGet(request, response);
        verify(request, atLeast(1)).getParameter("name");

        assertFalse(clinic.getCurrentClient().hasPetWithName("_TestBird"));

        clinic.removeCurrentClient();
    }
}