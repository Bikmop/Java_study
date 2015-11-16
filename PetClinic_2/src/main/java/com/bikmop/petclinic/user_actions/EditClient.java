package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.Input;
import com.bikmop.petclinic.Output;
import com.bikmop.petclinic.UserAction;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;

/**
 * Класс реализует редактирование клиента
 */
public class EditClient extends UserAction {
    /**
     * Строковые константы вывода
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
     * Конструктор
     * @param clinic Клиника
     * @param input Реализация интерфейса ввода
     * @param output Реализация интерфейса вывода
     */
    public EditClient(Clinic clinic, Input input, Output output) {
        this.clinic = clinic;
        this.input = input;
        this.output = output;
    }


    /**
     * Основная операция соответствующая данной реализации UserAction
     * @return Основная операция
     */
    @Override
    public MainOperation mainOperation() {
        return MainOperation.EDIT;
    }

    /**
     * Выполнить действие соответствующее реализации UserAction
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
     * Диалог выбора клиента для редактирования
     */
    private void askClientForEdit() {
        Client.SearchType searchType = askSearchType();
        String searchString = askStringForSearch();
        this.clinic.selectFirstMatchingClient(searchType, searchString);
        showFoundClient();
    }

    /**
     * Проверка найден ли клиент по запросу
     * @return Найден ли клиент
     */
    private boolean isClientFound() {
        return getCurrentClient() != null;
    }

    /**
     * Диалог редактирования текущего клиента
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
     * Список операций редактирования клиента
     */
    private enum EditClientOperation {
        /** Переименование клиента */
        RENAME_CLIENT,
        /** Удаление клиента */
        DELETE_CLIENT,
        /** Переименование животного */
        RENAME_PET,
        /** Добавление животного */
        ADD_PET,
        /** Удаление животного */
        DELETE_PET;

        /**
         * Строковые константы для операций редактирования клиента
         */
        private static final String STRING_FOR_RENAME_CLIENT = "1";
        private static final String STRING_FOR_DELETE_CLIENT = "2";
        private static final String STRING_FOR_RENAME_PET = "3";
        private static final String STRING_FOR_ADD_PET = "4";
        private static final String STRING_FOR_DELETE_PET = "5";

        /**
         * Получить операцию редактирования по строке
         * @param operationString Строка операции
         * @return Операция редактирования клиента
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
     * Диалог выбора операции редактирования
     * @return Операция редактирования
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
     * Диалог выбора корректной операции редактирования
     * Повторение в цикле до выбора корректной операции
     * @return Операция редактирования
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
     *  Показать найденного клиента
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
     * Получить текущего клиента клиники
     * @return Текущий клиент
     */
    private Client getCurrentClient() {
        return this.clinic.getCurrentClient();
    }

    /**
     * Диалог переименования текущего клиента
     */
    private void clientRenamingDialog() {
        this.output.print(ENTER_NEW_NAME);
        String newName = askString();
        this.clinic.renameCurrentClient(newName);
        showClientChangesAfterEditing(getCurrentClient());
    }

    /**
     * Показать измененного клиента после редактирования
     * @param client Клиент
     */
    private void showClientChangesAfterEditing(Client client) {
        this.output.println(CHANGES_AFTER_EDITING);
        this.output.println(client.toString());
    }

    /**
     * Диалог удаления текущего клиента
     */
    private void clientRemovingDialog() {
        this.clinic.removeCurrentClient();
        this.output.println(CLIENT_DELETED);
    }

    /**
     * Диалог переименования животного
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
     * Диалог добавления животного
     */
    private void petAddingDialog() {
        Pet pet = askOnePet();
        Client client = getCurrentClient();
        addPetForClient(client, pet);
        this.output.println(BLANK_LINE);

        showClientChangesAfterEditing(client);
    }

    /**
     * Диалог удаления животного
     */
    private void petRemovingDialog() {
        this.output.print(ENTER_PET_NAME_TO_DELETE);
        String petName = askNotBlankString();
        Client client = getCurrentClient();
        client.removePetByName(petName);

        showClientChangesAfterEditing(client);
    }

}
