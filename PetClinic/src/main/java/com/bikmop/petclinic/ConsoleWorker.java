package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;

public class ConsoleWorker {
    private static final String CLINICS_CLIENTS = "OUR CLIENTS:";
    private static final String CLIENTS_NOT_FOUND = "No clients found!";
    private static final String CLIENT_NOT_SELECTED = "Client is not selected!";
    private static final String SELECTED_CLIENT = "Selected client:";
    private static final String BLANK_LINE = "";



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
}
