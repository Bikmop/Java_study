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

    private Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

/*    @Test
    public void testRemovePet() throws ServletException, IOException {
        clinic.addClient(createAnna());
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "XX 33335789");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("Python");

        assertTrue(clinic.getCurrentClient().hasPetWithName("Python"));

        new PetDeleteServlet().doGet(request, response);
        verify(request, atLeast(1)).getParameter("name");

        assertFalse(clinic.getCurrentClient().hasPetWithName("Python"));

    }*/
}