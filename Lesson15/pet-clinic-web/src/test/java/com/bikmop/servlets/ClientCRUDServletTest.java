package com.bikmop.servlets;


import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Bird;
import com.bikmop.petclinic.pet.Reptile;
import com.bikmop.petclinic.pet.Rodent;
import com.bikmop.petclinic.pet.SomePet;
import com.bikmop.store.ClinicSingleton;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

public class ClientCRUDServletTest extends Mockito {
    final ClinicSingleton clinic = ClinicSingleton.getInstance();

// EDIT:
    @Test
    public void testEditClientUpdate() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("name")).thenReturn("_TestName02");
        when(request.getParameter("update")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/EditClient.jsp")).thenReturn(dispatcher);

        assertFalse(clinic.findClients(Client.SearchType.NAME_FULL, "_TestName01").isEmpty());
        assertTrue(clinic.findClients(Client.SearchType.NAME_FULL, "_TestName02").isEmpty());
        assertEquals("_TestName01", clinic.getCurrentClient().getFullName());

        new EditClientServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("update");
        verify(dispatcher).forward(request, response);

        assertFalse(clinic.findClients(Client.SearchType.NAME_FULL, "_TestName02").isEmpty());
        assertTrue(clinic.findClients(Client.SearchType.NAME_FULL, "_TestName01").isEmpty());
        assertEquals("_TestName02", clinic.getCurrentClient().getFullName());

        clinic.removeCurrentClient();
    }

    @Test
    public void testEditClientUpdateTheSameName() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("name")).thenReturn("_TestName01");
        when(request.getParameter("update")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/EditClient.jsp")).thenReturn(dispatcher);

        assertEquals(1, clinic.findClients(Client.SearchType.NAME_FULL, "_TestName01").size());
        assertEquals("_TestName01", clinic.getCurrentClient().getFullName());

        new EditClientServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("update");
        verify(dispatcher).forward(request, response);

        assertEquals(1, clinic.findClients(Client.SearchType.NAME_FULL, "_TestName01").size());

        clinic.removeCurrentClient();
    }

    @Test
    public void testEditClientRemove() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("remove")).thenReturn("");
        when(request.getRequestDispatcher("/view/clinic/ClinicView.jsp")).thenReturn(dispatcher);

        assertFalse(clinic.findClients(Client.SearchType.NAME_FULL, "_TestName01").isEmpty());
        assertEquals("_TestName01", clinic.getCurrentClient().getFullName());

        new EditClientServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("remove");

        assertTrue(clinic.findClients(Client.SearchType.NAME_FULL, "_TestName01").isEmpty());
        assertTrue(clinic.getCurrentClient() == null);
    }

    @Test
    public void testEditClientAddPet() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("petName")).thenReturn("_TestFish");
        when(request.getParameter("petType")).thenReturn("3");
        when(request.getParameter("addPet")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/EditClient.jsp")).thenReturn(dispatcher);

        assertFalse(clinic.getCurrentClient().hasPetWithName("_TestFish"));

        new EditClientServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameter("petType");
        verify(request, atLeast(1)).getParameter("addPet");
        verify(dispatcher).forward(request, response);

        assertTrue(clinic.getCurrentClient().hasPetWithName("_TestFish"));

        clinic.removeCurrentClient();
    }

    @Test
    public void testEditClientAddPetEmptyName() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("petName")).thenReturn("");
        when(request.getParameter("petType")).thenReturn("3");
        when(request.getParameter("addPet")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/EditClient.jsp")).thenReturn(dispatcher);

        assertFalse(clinic.getCurrentClient().hasPetWithName(""));

        new EditClientServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameter("addPet");
        verify(dispatcher).forward(request, response);

        assertFalse(clinic.getCurrentClient().hasPetWithName(""));

        clinic.removeCurrentClient();
    }

    @Test
    public void testEditClientById() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("id")).thenReturn("_TestId");
        when(request.getRequestDispatcher("/view/client/EditClient.jsp")).thenReturn(dispatcher);

        new EditClientServlet().doGet(request, response);
        verify(dispatcher).forward(request, response);

        clinic.removeCurrentClient();
    }


//ADD:
    @Test
    public void testAddClient() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        int initialSize = clinic.getClients().size();

        when(request.getParameter("name")).thenReturn("_TestName");
        when(request.getParameter("id")).thenReturn("_TestId");
        when(request.getParameter("add")).thenReturn("");
        when(request.getRequestDispatcher("/client/edit")).thenReturn(dispatcher);

        new AddClientServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("add");
        verify(dispatcher).forward(request, response);

        assertEquals(initialSize + 1, clinic.getClients().size());

        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        assertEquals("_TestName", clinic.getCurrentClient().getFullName());
        clinic.removeCurrentClient();
    }

    @Test
    public void testAddClientEmptyId() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        int initialSize = clinic.getClients().size();

        when(request.getParameter("name")).thenReturn("_TestName");
        when(request.getParameter("id")).thenReturn("");
        when(request.getParameter("add")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/AddClient.jsp")).thenReturn(dispatcher);

        new AddClientServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("add");

        assertEquals(initialSize, clinic.getClients().size());

        clinic.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "_TestName");
        assertNull(clinic.getCurrentClient());
    }

    @Test
    public void testAddClientExistingId() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        int initialSize = clinic.getClients().size();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("name")).thenReturn("_TestName02");
        when(request.getParameter("id")).thenReturn("_TestId");
        when(request.getParameter("add")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/AddClient.jsp")).thenReturn(dispatcher);

        new AddClientServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("add");

        assertEquals(initialSize, clinic.getClients().size());

        clinic.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "_TestName02");
        assertNull(clinic.getCurrentClient());

        clinic.selectFirstMatchingClient(Client.SearchType.NAME_FULL, "_TestName01");
        clinic.removeCurrentClient();
    }

    @Test
    public void testAddClientDoGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/view/client/AddClient.jsp")).thenReturn(dispatcher);

        new AddClientServlet().doGet(request, response);

        verify(dispatcher).forward(request, response);
    }


//VIEW:
    @Test
    public void testClinicViewPressAdd() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("addClient")).thenReturn("");
        when(request.getRequestDispatcher("/view/client/AddClient.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("addClient");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testClinicViewSearchEmptyClinicEmptyFilters() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("id")).thenReturn("");
        when(request.getParameter("petName")).thenReturn("");
        when(request.getRequestDispatcher("/view/clinic/ClinicView.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("search");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testClinicViewSearchWithAllParameters() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        clinic.addPetToCurrentClient("100", "_TestPet");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("");
        when(request.getParameter("name")).thenReturn("_TestName01");
        when(request.getParameter("nameFull")).thenReturn("");
        when(request.getParameter("id")).thenReturn("_TestId");
        when(request.getParameter("idFull")).thenReturn("");
        when(request.getParameter("petName")).thenReturn("_TestPet");
        when(request.getRequestDispatcher("/view/clinic/ClinicView.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("search");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("nameFull");
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("idFull");
        verify(request, atLeast(1)).getParameter("petName");
        verify(dispatcher).forward(request, response);

        clinic.removeCurrentClient();
    }

    @Test
    public void testClinicViewPartSearch() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        clinic.addPetToCurrentClient("100", "_TestPet");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("id")).thenReturn("estid");
        when(request.getRequestDispatcher("/view/clinic/ClinicView.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("search");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("id");
        verify(dispatcher).forward(request, response);

        clinic.removeCurrentClient();
    }

    @Test
    public void testClinicViewClear() throws ServletException, IOException {
        clinic.addClient(new Client("_TestName01", "_TestId"));
        clinic.selectFirstMatchingClient(Client.SearchType.ID_FULL, "_TestId");
        clinic.addPetToCurrentClient("100", "_TestPet");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("clear")).thenReturn("");
        when(request.getParameter("name")).thenReturn("_TestName01");
        when(request.getParameter("nameFull")).thenReturn("");
        when(request.getParameter("id")).thenReturn("_TestId");
        when(request.getParameter("idFull")).thenReturn("");
        when(request.getParameter("petName")).thenReturn("_TestPet");
        when(request.getRequestDispatcher("/view/clinic/ClinicView.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request, response);

        verify(request, times(1)).getParameter("clear");
        verify(request, times(0)).getParameter("name");
        verify(request, times(0)).getParameter("nameFull");
        verify(request, times(0)).getParameter("id");
        verify(request, times(0)).getParameter("idFull");
        verify(request, times(0)).getParameter("petName");
        verify(dispatcher).forward(request, response);

        clinic.removeCurrentClient();
    }

}
