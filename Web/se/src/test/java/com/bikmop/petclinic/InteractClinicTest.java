package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.input_output.console.ConsoleOutput;
import com.bikmop.petclinic.input_output.console.InputForTest;
import com.bikmop.petclinic.input_output.Input;
import com.bikmop.petclinic.pet.*;
import org.junit.Test;

import java.util.Arrays;

public class InteractClinicTest {
    Clinic clinic = new Clinic();

    //Additional methods for tests:

    public void initialClinicFilling() {
        clinic.addClient(createAnna());
        clinic.addClient(createIvan());
        clinic.addClient(createPetr());
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


    //Tests:

    @Test
    public void testMainDialogShowAllAndQuit() throws Exception {
        final String[] userActions = {"1",  //Show all clients
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogFindPartId() throws Exception {
        final String[] userActions = {"2",  //Find
                "1",                        //by part of ID
                "XX",                       //to search
                "Y",                        //another search?
                "1",                        //by part of ID
                "XY",                       //to search
                "N",                        //another search?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogFindFullId() throws Exception {
        final String[] userActions = {"2",  //Find
                "2",                        //by full ID
                "XX",                       //to search and not found
                "Y",                        //another search?
                "2",                        //by part of ID
                "XY 89012345",              //to search
                "N",                        //another search?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogFindPartName() throws Exception {
        final String[] userActions = {"2",  //Find
                "3",                        //by part of name
                "van",                      //to search
                "N",                        //another search?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogFindFullName() throws Exception {
        final String[] userActions = {"2",  //Find
                "4",                        //by full name
                "Ivan Petrov",              //to search
                "N",                        //another search?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogFindFullPetsName() throws Exception {
        final String[] userActions = {"2",  //Find
                "5",                        //by full pet's name
                "Python",                   //to search
                "N",                        //another search?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogAddClientWithoutPets() throws Exception {
        final String[] userActions = {"3",  //Add
                "XY 89012345",              //not unique id
                "XY 88888888",              //unique id
                "John Smith",               //full name of the client
                "N",                        //add pets?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogAddMakeFullClientsArray() throws Exception {
        final String[] userActions = {"3",  //Add
                "XY 88888888",              //id
                "John Smith",               //full name of the client
                "N",                        //add pets?
                "3",                        //Add second client
                "XX 00000555",              //id
                "Sara Smith",               //full name of the client
                "N",                        //add pets?
                "3",                        //Add second client
                "XX 00000444",              //id
                "Sara Connor",              //full name of the client
                "N",                        //add pets?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogAddClientWithPets() throws Exception {
        final String[] userActions = {"3",  //Add
                "XY 88888888",              //unique id
                "John Smith",               //full name of the client
                "y",                        //add pets?
                "1",                        //type of pet = cat
                "Kitty",                    //name
                "y",                        //add another pet?
                "2",                        //type of pet = dog
                "Doggy",                    //name
                "y",                        //add another pet?
                "3",                        //type of pet = fish
                "Goldy",                    //name
                "y",                        //add another pet?
                "4",                        //type of pet = bird
                "Birdie",                    //name
                "y",                        //add another pet?
                "5",                        //type of pet = reptile
                "Snaky",                    //name
                "y",                        //add another pet?
                "6",                        //type of pet = rodent
                "Ratty",                    //name
                "y",                        //add another pet?
                "any string",               //type of pet = some pet
                "Unknown",                  //name
                "n",                        //add another pet?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditRenameClient() throws Exception {
        final String[] userActions = {"4",  //Edit
                "48",                       //incorrect type of client search
                "1",                        //correct type of client search = part of ID
                "xx",                       //part of id
                "1",                        //rename client
                "Anna Smith",               //new name
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditDeleteClient() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xy",                       //part of id
                "2",                        //delete client
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditRenamePet() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xx",                       //part of id
                "3",                        //rename pet
                "Kesha",                    //old name of the pet
                "Popka",                    //new name of the pet
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditAddPet() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xx",                       //part of id
                "4",                        //add pet
                "6",                        //type of pet
                "Ratty",                    //name of the pet
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditAddAlreadyInListPet() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xx",                       //part of id
                "4",                        //add pet
                "4",                        //type of pet
                "Kesha",                    //name of the pet
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditDeletePet() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xx",                       //part of id
                "5",                        //delete pet
                "Python",                   //name of the pet
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogCancel() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xx",                       //part of id
                "6",                        //cancel
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditWrongClient() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xxxxx",                    //not present part of id
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogEditUnknownOperation() throws Exception {
        final String[] userActions = {"4",  //Edit
                "1",                        //client search by part of ID
                "xx",                       //part of id
                "xxxxx",                    //unknown operation
                "2",                        //correct operation
                "n",                        //another editing?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());
        initialClinicFilling();

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogWrongMainOperation() throws Exception {
        final String[] userActions = {"44", //Wrong operation
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }


    //Test clinic without clients:

    @Test
    public void testMainDialogShowAllFromEmpty() throws Exception {
        final String[] userActions = {"1",  //Show all clients
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogFindFromEmpty() throws Exception {
        final String[] userActions = {"2",  //Find
                "1",                        //by part of ID
                "XX",                       //to search
                "N",                        //another search?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

    @Test
    public void testMainDialogAddClientToEmpty() throws Exception {
        final String[] userActions = {"3",  //Add
                "XY 88888888",              //id
                "John Smith",               //full name of the client
                "N",                        //add pets?
                "5"};                       //Quit program

        final Input input = new InputForTest(Arrays.asList(userActions).iterator());

        new InteractClinic(clinic, input, new ConsoleOutput()).mainDialog();
    }

}