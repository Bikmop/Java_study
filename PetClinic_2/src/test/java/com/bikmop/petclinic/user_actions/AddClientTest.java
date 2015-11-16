package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.Input;
import com.bikmop.petclinic.InteractClinic;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.console.ConsoleInput;
import com.bikmop.petclinic.console.ConsoleOutput;
import com.bikmop.petclinic.console.InputForTest;
import com.bikmop.petclinic.console.OutputForTest;
import com.bikmop.petclinic.pet.Bird;
import com.bikmop.petclinic.pet.Reptile;
import com.bikmop.petclinic.pet.Rodent;
import com.bikmop.petclinic.pet.SomePet;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AddClientTest {
    private static final String L = System.lineSeparator();

    private Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

    @Test
    public void testMainOperation() throws Exception {
        AddClient addClient = new AddClient(new Clinic(5), new ConsoleInput(), new ConsoleOutput());
        assertEquals(addClient.mainOperation(), MainOperation.ADD);
    }

    @Test
    public void testProcessAddWithoutPets() throws Exception {
        final String expected = String.format(
                "Adding a client...%s" +
                        "Please, enter a unique ID(not blank) of the client: " +
                        "Please, enter the full name of the client: %s" +
                        "%s" +
                        "Do you want to add pets? (y - for Yes, another - for No): %s" +
                        "Client added:%s" +
                        "John Smith    Id: XY 88888888    Pets: no pets%s", L, L, L, L, L, L);

        final String[] userActions = {
                "XY 88888888",
                "John Smith",
                "N"};

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        final OutputForTest out = new OutputForTest();

        new AddClient(new Clinic(5), input, out).process();
//        System.out.println(out.getOutput());

        assertEquals(expected, out.getOutput());

    }

    @Test
    public void testProcessAddNotUniqueId() throws Exception {
        final String expected = String.format(
                "Adding a client...%s" +
                        "Please, enter a unique ID(not blank) of the client: " +
                        "Id is already present!%s" +
                        "Please, enter a unique ID(not blank) of the client: " +
                        "Please, enter the full name of the client: %s" +
                        "%s" +
                        "Do you want to add pets? (y - for Yes, another - for No): %s" +
                        "Client added:%s" +
                        "John Smith    Id: XY 88888888    Pets: no pets%s", L, L, L, L, L, L, L);

        final String[] userActions = {
                "XX 33335789",
                "XY 88888888",
                "John Smith",
                "N"};

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        final OutputForTest out = new OutputForTest();

        Clinic clinic = new Clinic(5);
        clinic.addClient(createAnna());
        new AddClient(clinic, input, out).process();
//        System.out.println(out.getOutput());

        assertEquals(expected, out.getOutput());

    }



}