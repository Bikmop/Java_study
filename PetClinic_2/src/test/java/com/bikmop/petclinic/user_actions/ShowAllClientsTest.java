package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.console.ConsoleInput;
import com.bikmop.petclinic.console.ConsoleOutput;
import com.bikmop.petclinic.console.OutputForTest;
import com.bikmop.petclinic.pet.Bird;
import com.bikmop.petclinic.pet.Reptile;
import com.bikmop.petclinic.pet.Rodent;
import com.bikmop.petclinic.pet.SomePet;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShowAllClientsTest {

    private static final String L = System.lineSeparator();
    private Clinic clinic = new Clinic();
    private ConsoleInput in = new ConsoleInput();
    private final OutputForTest out = new OutputForTest();
    private ShowAllClients showAllClients;

    //Additional methods:
    private void initEmptyClinic() {
        this.showAllClients = new ShowAllClients(clinic, in, out);
    }

    private void initClinicWithClients() {
        clinic.addClient(createAnna());
        clinic.addClient(createIvan());
        this.showAllClients = new ShowAllClients(clinic, in, out);
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


    //Tests:
    @Test
    public void testMainOperation() throws Exception {
        initEmptyClinic();
        assertEquals(this.showAllClients.mainOperation(), MainOperation.SHOW_ALL_CLIENTS);
    }

    @Test
    public void testProcessEmptyClinic() throws Exception {
        String expected = String.format("OUR CLIENTS:%sNo clients found!%s%s", L, L, L);
        initEmptyClinic();
        this.showAllClients.process();

        assertEquals(expected, this.out.getOutput());
    }

    @Test
    public void testProcessClinicWithClients() throws Exception {
        final String expected = String.format(
                "OUR CLIENTS:" +
                "%sAnna Ivanova    Id: XX 33335789    " +
                "Pets: Bird 'Kesha',  Rodent 'Mickey',  Reptile 'Python',  Pet 'Snail'" +
                "%sIvan Petrov    Id: XY 01234567    Pets: no pets" +
                "%s%s", L, L, L, L);
        initClinicWithClients();
        this.showAllClients.process();

        assertEquals(expected, this.out.getOutput());
    }



}