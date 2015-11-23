package com.bikmop.petclinic.user_actions;


import com.bikmop.petclinic.*;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;


/**
 * Класс реализует диалог добавления клиента
 */
public class AddClient extends UserAction {
    /**
     * Строковые константы вывода
     */
    private static final String ADD_CLIENT = "Adding a client...";
    private static final String ENTER_CLIENT_ID = "Please, enter a unique ID(not blank) of the client: ";
    private static final String ID_PRESENT = "Id is already present!";
    private static final String ENTER_CLIENT_NAME = "Please, enter the full name of the client: ";
    private static final String CLIENT_ADDED = "Client added:";
    private static final String CLIENTS_DB_IS_FULL = "Clients database is full!!! " +
            "Please, remove some of the clients before adding another.";
    private static final String YES_OR_NO = " (y - for Yes, another - for No): ";
    private static final String ASK_ADD_PET = "Do you want to add pets?" + YES_OR_NO;
    private static final String ASK_ADD_ANOTHER_PET = "Add another pet?" + YES_OR_NO;


    /**
     * Конструктор
     * @param clinic Клиника
     * @param input Реализация интерфейса ввода
     * @param output Реализация интерфейса вывода
     */
    public AddClient(Clinic clinic, Input input, Output output) {
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
        return MainOperation.ADD;
    }

    /**
     * Выполнить действие соответствующее реализации UserAction
     */
    @Override
    public void process() {
        this.output.println(ADD_CLIENT);

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
            this.output.print(ENTER_CLIENT_ID);
            clientId = askNotBlankString();
            uniqueId = this.clinic.isUniqueClientId(clientId);
            if (!uniqueId)
                this.output.println(ID_PRESENT);
        }

        return clientId;
    }

    /**
     * Диалог ввода полного имени(может быть пустым) клиента
     * @return Полное имя клиента
     */
    private String askClientFullName() {
        this.output.println(ENTER_CLIENT_NAME);
        return askString();
    }

    /**
     * Добавление в клинику клиента с животными
     * @param client Клиент
     */
    private void addClientWithPets(Client client) {
        this.output.println(BLANK_LINE);
        this.clinic.addClient(client);
        askAddPets(client);
        this.output.println(BLANK_LINE);
        this.output.println(CLIENT_ADDED);
        showClient(client);
    }

    /**
     * Диалог добавления животных клиенту
     * @param client Клиент
     */
    private void askAddPets(Client client) {
        this.output.print(ASK_ADD_PET);
        String askAddPetOrNot = askString();

        while (isYes(askAddPetOrNot)) {
            Pet pet = askOnePet();
            addPetForClient(client, pet);

            askAddPetOrNot = askAnother(ASK_ADD_ANOTHER_PET);
        }
    }

}
