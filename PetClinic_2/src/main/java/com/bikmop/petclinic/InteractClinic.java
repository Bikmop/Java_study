package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;

/**
 * Класс для работы с клиникой домашних животных
 */
public class InteractClinic {
    /** Клиника */
    private final Clinic clinic;
    /** Интерфейс ввода */
    private final Input input;
    /** Интерфейс вывода */
    private final Output output;

    /**
     * Строковые константы вывода
     */
    private static final String WELCOME = "********************  WELCOME TO PET CLINIC  ********************";
    private static final String SELECT_MAIN_OPERATION = "Please, select operation.";
    private static final String MAIN_OPERATIONS_LIST = "(1 - Show all clients,  2 - Find,  3 - Add," +
            "  4 - Edit,  5 - Quit program): ";
    private static final String SELECT_CORRECT_OPERATION = "Select correct operation! : ";
    private static final String CLINICS_CLIENTS = "OUR CLIENTS:";
    private static final String SEARCH_CLIENT = "Client search...";
    private static final String SELECT_SEARCH_TYPE = "Please, select the type of client search.";
    private static final String SEARCH_TYPES_LIST = "(1 - part of ID,  2 - full ID,  3 - part of name," +
            "  4 - full name,  5 - full pet's name): ";
    private static final String SELECT_CORRECT_SEARCH_TYPE = "Select correct type! : ";
    private static final String ENTER_SEARCH = "Enter a search: ";
    private static final String SEARCH_RESULT = "Search result:";
    private static final String CLIENTS_NOT_FOUND = "No clients found!";
    private static final String YES_OR_NO = " (y - for Yes, another - for No): ";
    private static final String ASK_ANOTHER_SEARCH = "Another search?" + YES_OR_NO;
    private static final String ADD_CLIENT = "Adding a client...";
    private static final String ENTER_CLIENT_ID = "Please, enter a unique ID(not blank) of the client: ";
    private static final String ID_PRESENT = "Id is already present!";
    private static final String ENTER_CLIENT_NAME = "Please, enter the full name of the client: ";
    private static final String CLIENTS_DB_IS_FULL = "Clients database is full!!! " +
            "Please, remove some of the clients before adding another.";
    private static final String ASK_ADD_PET = "Do you want to add pets?" + YES_OR_NO;
    private static final String ASK_ADD_ANOTHER_PET = "Add another pet?" + YES_OR_NO;
    private static final String SELECT_PET_TYPE = "Please, select the type of pet";
    private static final String PET_TYPES = "(1 - cat,  2 - dog,  3 - fish,  4 - bird,  5 - reptile,  6 - rodent,  "
            + "another - pet not from list): ";
    private static final String ENTER_PET_NAME = "Please, enter the name of the pet: ";
    private static final String CLIENT_ADDED = "Client added:";
    private static final String SELECT_CLIENT_FOR_EDIT = "Client for editing selection...";
    private static final String SELECT_EDITING_OPERATION = "Please, select editing operation";
    private static final String EDITING_TYPES = "(1 - rename client,  2 - delete client,  3 - rename pet," +
            "  4 - add pet,  5 - delete pet): ";
    private static final String CLIENT_FOR_EDIT = "Client for edit is";
    private static final String CLIENT_NOT_FOUND = " not found!";
    private static final String CHANGES_AFTER_EDITING = "Changes:";
    private static final String CLIENT_DELETED = "Client deleted.";
    private static final String ENTER_NEW_PET_NAME = "Enter NEW name of the pet: ";
    private static final String ENTER_OLD_PET_NAME = "Enter OLD name of the pet: ";
    private static final String ENTER_NEW_NAME = "Enter new name: ";
    private static final String ENTER_PET_NAME_TO_DELETE = "Enter the name of the pet to delete: ";
    private static final String ASK_ANOTHER_EDIT = "Another editing?" + YES_OR_NO;
    private static final String GOOD_BYE = "Good bye.";
    private static final String ANSWER_YES = "y";
    private static final String BLANK_LINE = "";
    private static final String COLON = ":";

    /**
     * Конструктор
     * @param clinic Клиника
     * @param input Реализация интерфейса ввода
     * @param output Реализация интерфейса вывода
     */
    public InteractClinic(Clinic clinic, Input input, Output output) {
        this.clinic = clinic;
        this.input = input;
        this.output = output;
    }

    /**
     * Главный диалог с пользователем
     */
    public void mainDialog() {
        writeMessageLn(WELCOME);
        dialogWithUserTillQuit();
        writeMessageLn(BLANK_LINE);
        writeMessageLn(GOOD_BYE);
        closeReader();
    }

    /**
     * Вывести сообщение в output с переходом на новую строку
     * @param message Сообщение
     */
    private void writeMessageLn(String message) {
        this.output.println(message);
    }

    /**
     * Вывести сообщение в output без перехода на новую строку
     * @param message Сообщение
     */
    private void writeMessage(String message) {
        this.output.print(message);
    }

    /**
     * Диалог с пользователем до выхода из программы
     */
    private void dialogWithUserTillQuit() {
        boolean isQuit = false;

        while (!isQuit) {
            MainOperation operation = askMainOperation();
            if (operation == MainOperation.QUIT)
                isQuit = true;
            else
                processUserRequest(operation);
        }
    }

    /**
     * Список основных операций пользователя
     */
    private enum MainOperation {
        /** Показать всех клиентов */
        SHOW_ALL_CLIENTS,
        /** Поиск */
        FIND,
        /** Добавление */
        ADD,
        /** Редактирование */
        EDIT,
        /** Выход из программы */
        QUIT;

        /**
         * Строковые константы для основных операций
         */
        private static final String STRING_FOR_SHOW_ALL_CLIENTS = "1";
        private static final String STRING_FOR_FIND = "2";
        private static final String STRING_FOR_ADD = "3";
        private static final String STRING_FOR_EDIT = "4";
        private static final String STRING_FOR_QUIT = "5";

        /**
         * Получить основную операцию по строке
         * @param operationString Строка операции
         * @return Основная операция
         */
        private static MainOperation getMainOperationByString(String operationString) {
            switch (operationString) {
                case STRING_FOR_SHOW_ALL_CLIENTS:
                    return SHOW_ALL_CLIENTS;
                case STRING_FOR_FIND:
                    return FIND;
                case STRING_FOR_ADD:
                    return ADD;
                case STRING_FOR_EDIT:
                    return EDIT;
                case STRING_FOR_QUIT:
                    return QUIT;
                default: throw new UnsupportedOperationException();
            }
        }

    }

    /**
     * Диалог выбора основной операции пользователем
     * @return Основная операция
     */
    private MainOperation askMainOperation() {
        writeMessageLn(BLANK_LINE);
        writeMessageLn(SELECT_MAIN_OPERATION);
        writeMessage(MAIN_OPERATIONS_LIST);
        MainOperation operation = askCorrectMainOperation();
        writeMessageLn(BLANK_LINE);
        return operation;
    }

    /**
     * Диалог выбора корректной основной операции пользователем
     * Повторение в цикле до выбора корректной операции
     * @return Основная операция
     */
    private MainOperation askCorrectMainOperation() {
        MainOperation operation = null;
        boolean isCorrect = false;

        while (!isCorrect) {
            String typeString = askString();
            try {
                operation = MainOperation.getMainOperationByString(typeString);
                isCorrect = true;
            } catch (UnsupportedOperationException uoe) {
                writeMessage(SELECT_CORRECT_OPERATION);
            }
        }

        return operation;
    }

    /**
     * Диалог ввода любой строки пользователем
     * @return Строка
     */
    private String askString() {
        return this.input.next();
    }

    /**
     * Обработать запрос пользователя
     * @param operation Основная операция
     */
    private void processUserRequest(MainOperation operation) {
        switch (operation) {
            case SHOW_ALL_CLIENTS:
                showAllClients();
                break;
            case FIND:
                showFoundClientsDialog();
                break;
            case ADD:
                addClientDialog();
                break;
            case EDIT:
                editClientDialog();
                break;
        }
    }

    /**
     * Показать всех клиентов в консоли
     */
    private void showAllClients() {
        writeMessageLn(CLINICS_CLIENTS);
        showClients(this.clinic.getClients());
        writeMessageLn(BLANK_LINE);
    }

    /**
     * Вывести клиентов в output
     * @param clients Массив клиентов
     */
    private void showClients(Client[] clients) {
        boolean hasClients = false;

        for (Client client : clients)
            if (client != null) {
                showClient(client);
                hasClients = true;
            }

        if (!hasClients)
            writeMessageLn(CLIENTS_NOT_FOUND);
    }

    /**
     * Показать одного клиента в output
     * @param client Клиент
     */
    private void showClient(Client client) {
        writeMessageLn(client.toString());
    }

    /**
     * Диалог отображения найденных клиентов по запросу пользователя
     */
    private void showFoundClientsDialog() {
        String isAnotherSearch = ANSWER_YES;

        writeMessageLn(SEARCH_CLIENT);

        while (isYes(isAnotherSearch)) {
            Client.SearchType searchType = askSearchType();
            String searchString = askStringForSearch();
            Client[] clients = this.clinic.findClients(searchType, searchString);

            showSearchResult(clients);

            isAnotherSearch = askAnother(ASK_ANOTHER_SEARCH);
            writeMessageLn(BLANK_LINE);
        }

        writeMessageLn(BLANK_LINE);
    }

    /**
     * Проверка строки на соответствие ANSWER_YES в любом регистре
     * @param yesOrNo Строка для проверки
     * @return Соответствие строки
     */
    private static boolean isYes(String yesOrNo) {
        return yesOrNo.toLowerCase().trim().equals(ANSWER_YES);
    }

    /**
     * Диалог выбора типа поиска клиентов
     * @return Тип поиска
     */
    private Client.SearchType askSearchType() {
        writeMessageLn(SELECT_SEARCH_TYPE);
        writeMessage(SEARCH_TYPES_LIST);
        return askCorrectSearchType();
    }

    /**
     * Диалог выбора корректного типа поиска
     * Повторение в цикле до выбора корректного типа
     * @return Тип поиска
     */
    private Client.SearchType askCorrectSearchType() {
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

    /**
     * Диалог ввода строки поиска
     * @return Строка минимум с одним символом
     */
    private String askStringForSearch() {
        writeMessage(ENTER_SEARCH);
        return askNotBlankString();
    }

    /**
     * Показать в консоль результат поиска клиентов
     * @param clients Массив клиентов
     */
    private void showSearchResult(Client[] clients) {
        writeMessageLn(BLANK_LINE);
        writeMessageLn(SEARCH_RESULT);
        showClients(clients);
    }

    /**
     * Диалог ввода непустой строки пользователем
     * @return Непустая строка
     */
    private String askNotBlankString() {
        String string = BLANK_LINE;

        while (BLANK_LINE.equals(string)) {
            string = askString();
        }

        return string;
    }

    /**
     * Запросить повторение операции
     * @param askMessage Строка запроса
     * @return Строка ответа
     */
    private String askAnother(String askMessage) {
        writeMessageLn(BLANK_LINE);
        writeMessage(askMessage);
        return askString();
    }

    /**
     * Диалог добавления клиента
     */
    private void addClientDialog() {
        writeMessageLn(ADD_CLIENT);

        String id = askUniqueClientId();
        String fullName = askClientFullName();
        Client client = new Client(fullName, id);
        addClientWithPets(client);
    }

    /**
     * Диалог запроса уникального идентификатора пользователя
     * Проверка на отсутствие у других пользователей клиники
     * @return Строка уникального id
     */
    private String askUniqueClientId() {
        boolean uniqueId = false;
        String clientId = BLANK_LINE;

        while (!uniqueId) {
            writeMessage(ENTER_CLIENT_ID);
            clientId = askNotBlankString();
            uniqueId = this.clinic.isUniqueClientId(clientId);
            if (!uniqueId)
                writeMessageLn(ID_PRESENT);
        }

        return clientId;
    }

    /**
     * Диалог ввода полного имени клиента
     * Может быть пустым
     * @return Полное имя клиента
     */
    private String askClientFullName() {
        writeMessage(ENTER_CLIENT_NAME);
        return askString();
    }

    /**
     * Добавление в клинику клиента с животными
     * @param client Клиент
     */
    private void addClientWithPets(Client client) {
        writeMessageLn(BLANK_LINE);

        try {
            this.clinic.addClient(client);
            askAddPets(client);
            writeMessageLn(BLANK_LINE);
            writeMessageLn(CLIENT_ADDED);
            showClient(client);
        } catch (FullClientsArrayException e) {
            writeMessageLn(CLIENTS_DB_IS_FULL);
        }
    }

    /**
     * Диалог добавления животных клиенту
     * @param client Клиент
     */
    private void askAddPets(Client client) {
        writeMessage(ASK_ADD_PET);
        String askAddPetOrNot = askString();

        while (isYes(askAddPetOrNot)) {
            Pet pet = askOnePet();
            addPetForClient(client, pet);

            askAddPetOrNot = askAnother(ASK_ADD_ANOTHER_PET);
        }
    }

    /**
     * Диалог запроса одного животного
     * @return Животное
     */
    private Pet askOnePet() {
        PetType petType = askPetType();
        String petName = askPetName();

        return PetFactory.createPet(petType, petName);
    }

    /**
     * Диалог запроса типа животного
     * @return Тип животного
     */
    private PetType askPetType() {
        writeMessageLn(SELECT_PET_TYPE);
        writeMessage(PET_TYPES);
        return PetType.getPetTypeByString(askString());
    }

    /**
     * Диалог запроса имени животного
     * @return Строка с именем животного
     */
    private String askPetName() {
        writeMessage(ENTER_PET_NAME);
        return askNotBlankString();
    }

    /**
     * Добавление одного животного клиенту
     * @param client Клиент
     * @param pet Животное
     */
    private void addPetForClient(Client client, Pet pet) {
        try {
            client.addPet(pet);
        } catch (IllegalArgumentException iae) {
            writeMessage(iae.getMessage());
        }
    }

    /**
     * Диалог редактирования клиента
     */
    private void editClientDialog() {
        String askNewEdit = ANSWER_YES;
        writeMessageLn(SELECT_CLIENT_FOR_EDIT);

        while (isYes(askNewEdit)) {
            askClientForEdit();
            if (isClientFound()) {
                editClient();
            }

            askNewEdit = askAnother(ASK_ANOTHER_EDIT);
            writeMessageLn(BLANK_LINE);
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
     *  Показать найденного клиента
     */
    private void showFoundClient() {
        writeMessageLn(BLANK_LINE);
        writeMessage(CLIENT_FOR_EDIT);
        if (isClientFound()) {
            writeMessageLn(COLON);
            writeMessageLn(getCurrentClient().toString());
        } else {
            writeMessageLn(CLIENT_NOT_FOUND);
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
        writeMessageLn(BLANK_LINE);
        writeMessageLn(SELECT_EDITING_OPERATION);
        writeMessage(EDITING_TYPES);
        EditClientOperation operation = askCorrectEditingOperation();
        writeMessageLn(BLANK_LINE);
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
                writeMessage(SELECT_CORRECT_OPERATION);
            }
        }

        return operation;
    }

    /**
     * Диалог переименования текущего клиента
     */
    private void clientRenamingDialog() {
        writeMessage(ENTER_NEW_NAME);
        String newName = askString();
        this.clinic.renameCurrentClient(newName);
        showClientChangesAfterEditing(getCurrentClient());
    }

    /**
     * Показать измененного клиента после редактирования
     * @param client Клиент
     */
    private void showClientChangesAfterEditing(Client client) {
        writeMessageLn(CHANGES_AFTER_EDITING);
        writeMessageLn(client.toString());
    }

    /**
     * Диалог удаления текущего клиента
     */
    private void clientRemovingDialog() {
        this.clinic.removeCurrentClient();
        writeMessageLn(CLIENT_DELETED);
    }

    /**
     * Диалог переименования животного
     */
    private void petRenamingDialog() {
        writeMessage(ENTER_OLD_PET_NAME);
        String petOldName = askNotBlankString();
        writeMessage(ENTER_NEW_PET_NAME);
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
        writeMessageLn(BLANK_LINE);

        showClientChangesAfterEditing(client);
    }

    /**
     * Диалог удаления животного
     */
    private void petRemovingDialog() {
        writeMessage(ENTER_PET_NAME_TO_DELETE);
        String petName = askNotBlankString();
        Client client = getCurrentClient();
        client.removePetByName(petName);

        showClientChangesAfterEditing(client);
    }

    /**
     * Закрытие input-ридера
     */
    private void closeReader() {
        this.input.close();
    }

}
