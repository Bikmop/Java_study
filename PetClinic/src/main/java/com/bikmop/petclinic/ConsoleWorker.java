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
    private static final String ASK_ADD_PET = "Do you want to add pets? (y for Yes, another for No): ";
    private static final String ASK_ADD_ANOTHER_PET = "Add another pet? (y for Yes, another for No): ";
    private static final String ANSWER_YES = "y";
    private static final String SELECT_PET_TYPE = "Please, select the type of pet";
    private static final String PET_TYPES = "(1 - cat,  2 - dog,  3 - fish,  4 - bird,  5 - reptile,  6 - rodent,  "
            + "another - pet not from list): ";
    private static final String ENTER_PET_NAME = "Please, enter the name of the pet: ";
    private static final String ID_PRESENT = "Id is already present!";
    private static final String WELCOME = "********************  WELCOME TO PET CLINIC  ********************";
    private static final String SELECT_MAIN_OPERATION = "Please, select operation.";
    private static final String MAIN_OPERATIONS_LIST = "(1 - Show all clients,  2 - Find,  3 - Add," +
            "  4 - Edit,  5 - Quit program): ";
    private static final String SELECT_CORRECT_OPERATION = "Select correct operation!: ";
    private static final String GOOD_BYE = "Good bye.";
    private static final String SEARCH_CLIENT = "Client search...";
    private static final String SELECT_SEARCH_TYPE = "Please, select the type of client search.";
    private static final String SEARCH_TYPES_LIST = "(1 - part of ID,  2 - full ID,  3 - part of name," +
            "  4 - full name,  5 - full pet's name): ";
    private static final String SELECT_CORRECT_SEARCH_TYPE = "Select correct type!: ";
    private static final String ENTER_SEARCH = "Enter a search: ";
    private static final String ASK_ANOTHER_SEARCH = "Another search? (y for Yes, another for No): ";
    private static final String SEARCH_RESULT = "Search result:";
    private static final String EDIT_CLIENT = "Edit client...";
    private static final String ASK_ANOTHER_EDIT = "Another editing? (y for Yes, another for No): ";


    private static Scanner reader = new Scanner(System.in);


    public static String readString() {
        return reader.nextLine();
    }

    public static void mainDialog(Clinic clinic) {
        writeMessageLn(WELCOME);
        MainOperation operation = MainOperation.WRONG_OPERATION;

        while (operation != MainOperation.QUIT) {
            operation = MainOperation.WRONG_OPERATION;

            writeMessageLn(BLANK_LINE);
            writeMessageLn(SELECT_MAIN_OPERATION);
            writeMessage(MAIN_OPERATIONS_LIST);
            while (operation == MainOperation.WRONG_OPERATION) {
                String opString = askString();
                operation = MainOperation.getMainOperationByString(opString);
                if (operation == MainOperation.WRONG_OPERATION)
                    writeMessage(SELECT_CORRECT_OPERATION);
            }
            writeMessageLn(BLANK_LINE);

            switch (operation) {
                case SHOW_ALL_CLIENTS:
                    showAllClients(clinic);
                    break;
                case FIND:
                    showFoundClientsDialog(clinic);
                    break;
                case ADD:
                    addClientDialog(clinic);
                    break;
                case EDIT:
                    editClientDialog(clinic);
                    break;
            }

        }

        writeMessageLn(BLANK_LINE);
        writeMessage(GOOD_BYE);

    }

    public static void editClientDialog(Clinic clinic) {
        String askNewEdit = ANSWER_YES;

        writeMessageLn(EDIT_CLIENT);

        while (isYes(askNewEdit)) {

            Client.SearchType searchType = askSearchType();
            String searchString = askStringForSearch();
            clinic.selectFirstMatchingClient(searchType, searchString);
            writeMessageLn(BLANK_LINE);
            writeMessage("Client for edit is");
            if (clinic.getCurrentClient() != null) {
                writeMessageLn(":");
                writeMessageLn(clinic.getCurrentClient().toString());

                writeMessageLn(BLANK_LINE);
                writeMessageLn("Please, select editing operation");
                writeMessage("(1 - rename client,  2 - delete client,  3 - rename pet,  4 - add pet,  5 - delete pet): ");
                EditClientOperation operation = askCorrectEditingOperation();
                switch (operation) {
                    case RENAME_CLIENT:
                        writeMessage("Enter new name: ");
                        String newName = askString();
                        clinic.renameCurrentClient(newName);
                        writeMessageLn("Changes:");
                        writeMessageLn(clinic.getCurrentClient().toString());
                        break;
                    case DELETE_CLIENT:
                        clinic.removeCurrentClient();
                        writeMessageLn("Client deleted.");
                        break;
                    case RENAME_PET:
                        petRenamingDialog(clinic.getCurrentClient());
                        break;
                    case ADD_PET:
                        petAddingDialog(clinic.getCurrentClient());
                        break;
                    case DELETE_PET:
                        petRemovingDialog(clinic.getCurrentClient());
                        break;
                }

            } else {
                writeMessageLn(" not found!");
            }

            askNewEdit = askAnother(ASK_ANOTHER_EDIT);
            writeMessageLn(BLANK_LINE);
        }

        writeMessageLn(BLANK_LINE);
    }

    private static void petAddingDialog(Client client) {
        writeMessageLn(BLANK_LINE);
        Pet pet = askOnePet();
        tryAddPetForClient(client, pet);

        writeMessageLn(BLANK_LINE);
        writeMessageLn("Changes:");
        writeMessageLn(client.toString());
    }

    private static void petRemovingDialog(Client client) {
        writeMessage(BLANK_LINE);
        writeMessage("Enter the name of the pet to delete:");
        String petName = askNotBlankString();
        client.removePetByName(petName);

        writeMessageLn("Changes:");
        writeMessageLn(client.toString());
    }

    private static void petRenamingDialog(Client client) {


        writeMessage(BLANK_LINE);
        writeMessage("Enter OLD name of the pet: ");
        String petOldName = askNotBlankString();
        writeMessage("Enter NEW name of the pet: ");
        String petNewName = askNotBlankString();

        client.renamePet(petOldName, petNewName);
        writeMessageLn("Changes:");
        writeMessageLn(client.toString());
    }

    private static EditClientOperation askCorrectEditingOperation() {
        EditClientOperation operation = null;
        boolean isCorrectType = false;

        while (!isCorrectType) {
            String typeString = askString();
            try {
                operation = EditClientOperation.getEditClientOperationByString(typeString);
                isCorrectType = true;
            } catch (UnsupportedOperationException uoe) {
                writeMessage(SELECT_CORRECT_OPERATION);
            }
        }

        return operation;
    }

    private enum EditClientOperation {
        RENAME_CLIENT,
        DELETE_CLIENT,
        RENAME_PET,
        ADD_PET,
        DELETE_PET;

        private static EditClientOperation getEditClientOperationByString(String numberString) {
            switch (numberString) {
                case "1": return RENAME_CLIENT;
                case "2": return DELETE_CLIENT;
                case "3": return RENAME_PET;
                case "4": return ADD_PET;
                case "5": return DELETE_PET;
                default: throw new UnsupportedOperationException();
            }
        }
    }

    public static void showFoundClientsDialog(Clinic clinic) {
        String askNewSearch = ANSWER_YES;

        writeMessageLn(SEARCH_CLIENT);

        while (isYes(askNewSearch)) {
            Client.SearchType searchType = askSearchType();
            String searchString = askStringForSearch();
            Client[] clients = clinic.findClients(searchType, searchString);
            showSearchResult(clients);
            askNewSearch = askAnother(ASK_ANOTHER_SEARCH);
        }

        writeMessageLn(BLANK_LINE);
    }

    private static String askAnother(String askMessage) {
        writeMessageLn(BLANK_LINE);
        writeMessage(askMessage);
        return askString();
    }

    private static void showSearchResult(Client[] clients) {
        writeMessageLn(SEARCH_RESULT);
        showClients(clients);
    }

    private static String askStringForSearch() {
        writeMessage(ENTER_SEARCH);
        return askNotBlankString();
    }

    private static Client.SearchType askSearchType() {
        writeMessageLn(SELECT_SEARCH_TYPE);
        writeMessage(SEARCH_TYPES_LIST);
        return askCorrectSearchType();
    }

    private static Client.SearchType askCorrectSearchType() {
        Client.SearchType searchType = null;
        boolean isCorrectType = false;

        while (!isCorrectType) {
            String typeString = askString();
            try {
                searchType = Client.SearchType.getSearchTypeByString(typeString);
                isCorrectType = true;
            } catch (IllegalArgumentException iae) {
                writeMessage(SELECT_CORRECT_SEARCH_TYPE);
            }
        }

        return searchType;
    }

    private enum MainOperation {
        SHOW_ALL_CLIENTS,
        FIND,
        ADD,
        EDIT,
        QUIT,
        WRONG_OPERATION;

        private static MainOperation getMainOperationByString(String numberString) {
            switch (numberString) {
                case "1": return SHOW_ALL_CLIENTS;
                case "2": return FIND;
                case "3": return ADD;
                case "4": return EDIT;
                case "5": return QUIT;
                default: return WRONG_OPERATION;
            }
        }
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
        List<Pet> pets = new ArrayList<>();

        writeMessage(ASK_ADD_PET);
        String askAddPetOrNot = askString();

        while (isYes(askAddPetOrNot)) {
            Pet pet = askOnePet();
            tryAddPetForClient(client, pet);
            askAddPetOrNot = askAnother(ASK_ADD_ANOTHER_PET);
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
        return askNotBlankString();
    }

    private static PetType askPetType() {
        writeMessageLn(SELECT_PET_TYPE);
        writeMessage(PET_TYPES);

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
            uniqueId = clinic.isUniqueClientId(clientId);
            if (!uniqueId)
                writeMessageLn(ID_PRESENT);
        }

        return clientId;
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
