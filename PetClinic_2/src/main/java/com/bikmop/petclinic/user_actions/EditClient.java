package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.Input;
import com.bikmop.petclinic.Output;
import com.bikmop.petclinic.UserAction;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;

/**
 * ����� ��������� �������������� �������
 */
public class EditClient extends UserAction {
    /**
     * ��������� ��������� ������
     */
    private static final String SELECT_CLIENT_FOR_EDIT = "Client for editing selection...";
    private static final String YES_OR_NO = " (y - for Yes, another - for No): ";
    private static final String ASK_ANOTHER_EDIT = "Another editing?" + YES_OR_NO;
    private static final String SELECT_EDITING_OPERATION = "Please, select editing operation";
    private static final String EDITING_TYPES = "(1 - rename client,  2 - delete client,  3 - rename pet," +
            "  4 - add pet,  5 - delete pet): ";
    private static final String SELECT_CORRECT_OPERATION = "Select correct operation! : ";
    private static final String CLIENT_FOR_EDIT = "Client for edit is";
    private static final String COLON = ":";
    private static final String CLIENT_NOT_FOUND = " not found!";
    private static final String ENTER_NEW_NAME = "Enter new name: ";
    private static final String CHANGES_AFTER_EDITING = "Changes:";
    private static final String CLIENT_DELETED = "Client deleted.";
    private static final String ENTER_NEW_PET_NAME = "Enter NEW name of the pet: ";
    private static final String ENTER_OLD_PET_NAME = "Enter OLD name of the pet: ";
    private static final String ENTER_PET_NAME_TO_DELETE = "Enter the name of the pet to delete: ";


    /**
     * �����������
     * @param clinic �������
     * @param input ���������� ���������� �����
     * @param output ���������� ���������� ������
     */
    public EditClient(Clinic clinic, Input input, Output output) {
        this.clinic = clinic;
        this.input = input;
        this.output = output;
    }


    /**
     * �������� �������� ��������������� ������ ���������� UserAction
     * @return �������� ��������
     */
    @Override
    public MainOperation mainOperation() {
        return MainOperation.EDIT;
    }

    /**
     * ��������� �������� ��������������� ���������� UserAction
     */
    @Override
    public void process() {
        String askNewEdit = ANSWER_YES;
        this.output.println(SELECT_CLIENT_FOR_EDIT);

        while (isYes(askNewEdit)) {
            askClientForEdit();
            if (isClientFound()) {
                editClient();
            }

            askNewEdit = askAnother(ASK_ANOTHER_EDIT);
            this.output.println(BLANK_LINE);
        }
    }


    /**
     * ������ ������ ������� ��� ��������������
     */
    private void askClientForEdit() {
        Client.SearchType searchType = askSearchType();
        String searchString = askStringForSearch();
        this.clinic.selectFirstMatchingClient(searchType, searchString);
        showFoundClient();
    }

    /**
     * �������� ������ �� ������ �� �������
     * @return ������ �� ������
     */
    private boolean isClientFound() {
        return getCurrentClient() != null;
    }

    /**
     * ������ �������������� �������� �������
     */
    private void editClient() {
        EditClientOperation operation = askEditingOperation();
        switch (operation) {
            case RENAME_CLIENT:
                clientRenamingDialog();
                break;
            case DELETE_CLIENT:
                clientRemovingDialog();
                break;
            case RENAME_PET:
                petRenamingDialog();
                break;
            case ADD_PET:
                petAddingDialog();
                break;
            case DELETE_PET:
                petRemovingDialog();
                break;
        }
    }

    /**
     * ������ �������� �������������� �������
     */
    private enum EditClientOperation {
        /** �������������� ������� */
        RENAME_CLIENT,
        /** �������� ������� */
        DELETE_CLIENT,
        /** �������������� ��������� */
        RENAME_PET,
        /** ���������� ��������� */
        ADD_PET,
        /** �������� ��������� */
        DELETE_PET;

        /**
         * ��������� ��������� ��� �������� �������������� �������
         */
        private static final String STRING_FOR_RENAME_CLIENT = "1";
        private static final String STRING_FOR_DELETE_CLIENT = "2";
        private static final String STRING_FOR_RENAME_PET = "3";
        private static final String STRING_FOR_ADD_PET = "4";
        private static final String STRING_FOR_DELETE_PET = "5";

        /**
         * �������� �������� �������������� �� ������
         * @param operationString ������ ��������
         * @return �������� �������������� �������
         */
        private static EditClientOperation getEditClientOperationByString(String operationString) {
            switch (operationString) {
                case STRING_FOR_RENAME_CLIENT:
                    return RENAME_CLIENT;
                case STRING_FOR_DELETE_CLIENT:
                    return DELETE_CLIENT;
                case STRING_FOR_RENAME_PET:
                    return RENAME_PET;
                case STRING_FOR_ADD_PET:
                    return ADD_PET;
                case STRING_FOR_DELETE_PET:
                    return DELETE_PET;
                default: throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * ������ ������ �������� ��������������
     * @return �������� ��������������
     */
    private EditClientOperation askEditingOperation() {
        this.output.println(BLANK_LINE);
        this.output.println(SELECT_EDITING_OPERATION);
        this.output.print(EDITING_TYPES);
        EditClientOperation operation = askCorrectEditingOperation();
        this.output.println(BLANK_LINE);
        return operation;
    }

    /**
     * ������ ������ ���������� �������� ��������������
     * ���������� � ����� �� ������ ���������� ��������
     * @return �������� ��������������
     */
    private EditClientOperation askCorrectEditingOperation() {
        EditClientOperation operation = null;
        boolean isCorrect = false;

        while (!isCorrect) {
            String typeString = askString();
            try {
                operation = EditClientOperation.getEditClientOperationByString(typeString);
                isCorrect = true;
            } catch (UnsupportedOperationException uoe) {
                this.output.print(SELECT_CORRECT_OPERATION);
            }
        }

        return operation;
    }

    /**
     *  �������� ���������� �������
     */
    private void showFoundClient() {
        this.output.println(BLANK_LINE);
        this.output.print(CLIENT_FOR_EDIT);
        if (isClientFound()) {
            this.output.println(COLON);
            this.output.println(getCurrentClient().toString());
        } else {
            this.output.println(CLIENT_NOT_FOUND);
        }
    }

    /**
     * �������� �������� ������� �������
     * @return ������� ������
     */
    private Client getCurrentClient() {
        return this.clinic.getCurrentClient();
    }

    /**
     * ������ �������������� �������� �������
     */
    private void clientRenamingDialog() {
        this.output.print(ENTER_NEW_NAME);
        String newName = askString();
        this.clinic.renameCurrentClient(newName);
        showClientChangesAfterEditing(getCurrentClient());
    }

    /**
     * �������� ����������� ������� ����� ��������������
     * @param client ������
     */
    private void showClientChangesAfterEditing(Client client) {
        this.output.println(CHANGES_AFTER_EDITING);
        this.output.println(client.toString());
    }

    /**
     * ������ �������� �������� �������
     */
    private void clientRemovingDialog() {
        this.clinic.removeCurrentClient();
        this.output.println(CLIENT_DELETED);
    }

    /**
     * ������ �������������� ���������
     */
    private void petRenamingDialog() {
        this.output.print(ENTER_OLD_PET_NAME);
        String petOldName = askNotBlankString();
        this.output.print(ENTER_NEW_PET_NAME);
        String petNewName = askNotBlankString();

        Client client = getCurrentClient();
        client.renamePet(petOldName, petNewName);

        showClientChangesAfterEditing(client);
    }

    /**
     * ������ ���������� ���������
     */
    private void petAddingDialog() {
        Pet pet = askOnePet();
        Client client = getCurrentClient();
        addPetForClient(client, pet);
        this.output.println(BLANK_LINE);

        showClientChangesAfterEditing(client);
    }

    /**
     * ������ �������� ���������
     */
    private void petRemovingDialog() {
        this.output.print(ENTER_PET_NAME_TO_DELETE);
        String petName = askNotBlankString();
        Client client = getCurrentClient();
        client.removePetByName(petName);

        showClientChangesAfterEditing(client);
    }

}
