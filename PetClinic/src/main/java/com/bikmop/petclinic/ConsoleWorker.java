package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleWorker {
    private static final String CLINICS_CLIENTS = "OUR CLIENTS:";
    private static final String CLIENTS_NOT_FOUND = "No clients found!";
    private static final String CLIENT_NOT_SELECTED = "Client is not selected!";
    private static final String SELECTED_CLIENT = "Selected client:";
    private static final String BLANK_LINE = "";
    private static final String ADD_CLIENT = "Adding a client...";
    private static final String ENTER_CLIENT_ID = "Please, enter a unique ID(not blank) of the client: ";
    private static final String ENTER_CLIENT_NAME = "Please, enter the full name of the client: ";
    private static final String ASK_ADD_PET = "Do you want to add pets? (y, n): ";
    private static final String ASK_ADD_ANOTHER_PET = "Add another pet? (y, n): ";
    private static final String ANSWER_YES = "y";
    private static final String SELECT_PET_TYPE = "Please, select the type of pet";
    private static final String PET_TYPES = "(1 - cat,  2 - dog,  3 - fish,  4 - bird,  5 - reptile,  6 - rodent,  "
            + "another - pet not from list):";
    private static final String ENTER_PET_NAME = "Please, enter the name of the pet: ";
    private static final String ID_PRESENT = "Id is already present!";

    private static Scanner reader = new Scanner(System.in);


    public static String readString() {
        return reader.nextLine();
    }

    public static void showAllClients(Clinic clinic) {
        writeMessageLn(CLINICS_CLIENTS);
        showClients(clinic.getClients());
        writeMessageLn(BLANK_LINE);
    }

    public static void showClients(Client[] clients) {
        boolean hasClients = false;

        for (Client client : clients)
            if (client != null) {
                showClient(client);
                hasClients = true;
            }

        if (!hasClients)
            writeMessageLn(CLIENTS_NOT_FOUND);
    }

    public static void showCurrentClient(Clinic clinic) {
        if (clinic.getCurrentClient() == null)
            writeMessageLn(CLIENT_NOT_SELECTED);
        else {
            writeMessageLn(SELECTED_CLIENT);
            showClient(clinic.getCurrentClient());
        }
        writeMessageLn(BLANK_LINE);
    }

    private static void showClient(Client client) {
        writeMessageLn(client.toString());
    }

    private static void showPet(Pet pet) {
        writeMessage(pet.toString());
    }

    public static void writeMessageLn(String message) {
        System.out.println(message);
    }

    public static void writeMessage(String message) {
        System.out.print(message);
    }

    public static void addClientDialog(Clinic clinic) {
        writeMessageLn(ADD_CLIENT);

        String id = askUniqueClientId(clinic);
        String fullName = askClientFullName();
        Client client = new Client(fullName, id);
        askClientPets(client);

        clinic.addClient(client);
    }

    private static void askClientPets(Client client) {
        List<Pet> pets = new ArrayList<Pet>();

        writeMessage(ASK_ADD_PET);
        String askAddPetOrNot = askString();

        while (isYes(askAddPetOrNot)) {
            Pet pet = askOnePet();
            tryAddPetForClient(client, pet);

            writeMessageLn(BLANK_LINE);
            writeMessage(ASK_ADD_ANOTHER_PET);
            askAddPetOrNot = askString();
        }
    }

    private static void tryAddPetForClient(Client client, Pet pet) {
        try {
            client.addPet(pet);
        } catch (IllegalArgumentException iae) {
            writeMessage(iae.getMessage());
        }
    }

    private static Pet askOnePet() {
        PetType petType = askPetType();
        String petName = askPetName();

        return PetFactory.createPet(petType, petName);
    }

    private static String askPetName() {
        writeMessage(ENTER_PET_NAME);
        return askString();
    }

    private static PetType askPetType() {
        writeMessageLn(SELECT_PET_TYPE);
        writeMessageLn(PET_TYPES);

        return getPetTypeByString(askString());
    }

    private static PetType getPetTypeByString(String petTypeStr) {
        PetType petType;
        try {
            int typeNumber = Integer.parseInt(petTypeStr);
            petType = PetType.getPetTypeByNumber(typeNumber);
        } catch (NumberFormatException nfe) {
            petType = PetType.SOME_PET;
        }
        return petType;
    }

    private static boolean isYes(String yesOrNo) {
        return yesOrNo.toLowerCase().trim().equals(ANSWER_YES);
    }

    private static String askClientFullName() {
        writeMessage(ENTER_CLIENT_NAME);
        return askString();
    }

    private static String askUniqueClientId(Clinic clinic) {
        boolean uniqueId = false;
        String clientId = BLANK_LINE;

        while (!uniqueId) {
            writeMessage(ENTER_CLIENT_ID);
            clientId = askNotBlankString();
            uniqueId = isUniqueClientId(clinic, clientId);
            if (!uniqueId)
                writeMessageLn(ID_PRESENT);
        }

        return clientId;
    }

    private static boolean isUniqueClientId(Clinic clinic, String clientId) {
        boolean uniqueId = true;

        for (Client client : clinic.getClients())
            if (client != null && client.getId().equals(clientId)) {
                uniqueId = false;
                break;
            }

        return uniqueId;
    }

    private static String askNotBlankString() {
        String s = BLANK_LINE;

        while (BLANK_LINE.equals(s)) {
            s = askString();
        }

        return s;
    }

    private static String askString() {
        return readString();
    }

}
