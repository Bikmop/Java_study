package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;
import com.bikmop.petclinic.user_actions.MainOperation;

import java.util.List;

/**
 * Абстрактный класс действия пользователя. Для паттерна "команда".
 */
public abstract class UserAction {
    /** Клиника */
    protected Clinic clinic;
    /** Интерфейс ввода */
    protected Input input;
    /** Интерфейс вывода */
    protected Output output;

    /**
     * Строковые константы вывода
     */
    protected static final String ANSWER_YES = "y";
    protected static final String BLANK_LINE = "";
    private static final String CLIENTS_NOT_FOUND = "No clients found!";
    private static final String SELECT_PET_TYPE = "Please, select the type of pet";
    private static final String PET_TYPES = "(1 - cat,  2 - dog,  3 - fish,  4 - bird,  5 - reptile,  6 - rodent,  "
            + "another - pet not from list): ";
    private static final String ENTER_PET_NAME = "Please, enter the name of the pet: ";
    private static final String SELECT_SEARCH_TYPE = "Please, select the type of client search.";
    private static final String SEARCH_TYPES_LIST = "(1 - part of ID,  2 - full ID,  3 - part of name," +
            "  4 - full name,  5 - full pet's name): ";
    private static final String SELECT_CORRECT_SEARCH_TYPE = "Select correct type! : ";
    private static final String ENTER_SEARCH = "Enter a search: ";

    /**
     * Основная операция в зависимости от реализации UserAction
     * @return Основная операция
     */
    public abstract MainOperation mainOperation();

    /**
     * Выполнить действие в зависимости от реализации UserAction
     */
    public abstract void process();


    /**
     * Проверка строки на соответствие ANSWER_YES в любом регистре
     * @param yesOrNo Строка для проверки
     * @return Соответствие строки
     */
    protected static boolean isYes(String yesOrNo) {
        return yesOrNo.toLowerCase().trim().equals(ANSWER_YES);
    }

    /**
     * Вывести клиентов в output
     * @param clients Список клиентов
     */
    protected void showClients(List<Client> clients) {
        boolean hasClients = false;

        for (Client client : clients)
            if (client != null) {
                showClient(client);
                hasClients = true;
            }

        if (!hasClients)
            this.output.println(CLIENTS_NOT_FOUND);
    }

    /**
     * Показать одного клиента в output
     * @param client Клиент
     */
    protected void showClient(Client client) {
        this.output.println(client.toString());
    }

    /**
     * Диалог ввода непустой строки пользователем
     * @return Непустая строка
     */
    protected String askNotBlankString() {
        String string = BLANK_LINE;

        while (BLANK_LINE.equals(string)) {
            string = askString();
        }

        return string;
    }

    /**
     * Диалог ввода любой строки пользователем
     * @return Строка
     */
    protected String askString() {
        return this.input.next();
    }

    /**
     * Диалог запроса одного животного
     * @return Животное
     */
    protected Pet askOnePet() {
        PetType petType = askPetType();
        String petName = askPetName();

        return PetFactory.createPet(petType, petName);
    }

    /**
     * Диалог запроса типа животного
     * @return Тип животного
     */
    private PetType askPetType() {
        this.output.println(SELECT_PET_TYPE);
        this.output.print(PET_TYPES);
        return PetType.getPetTypeByString(askString());
    }

    /**
     * Диалог запроса имени животного
     * @return Строка с именем животного
     */
    private String askPetName() {
        this.output.print(ENTER_PET_NAME);
        return askNotBlankString();
    }

    /**
     * Запросить повторение операции
     * @param askMessage Строка запроса
     * @return Строка ответа
     */
    protected String askAnother(String askMessage) {
        this.output.println(BLANK_LINE);
        this.output.print(askMessage);
        return askString();
    }

    /**
     * Добавление одного животного клиенту
     * @param client Клиент
     * @param pet Животное
     */
    protected void addPetForClient(Client client, Pet pet) {
        try {
            client.addPet(pet);
        } catch (IllegalArgumentException iae) {
            this.output.print(iae.getMessage());
        }
    }

    /**
     * Диалог выбора типа поиска клиентов
     * @return Тип поиска
     */
    protected Client.SearchType askSearchType() {
        this.output.println(SELECT_SEARCH_TYPE);
        this.output.print(SEARCH_TYPES_LIST);
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
                this.output.print(SELECT_CORRECT_SEARCH_TYPE);
            }
        }

        return searchType;
    }

    /**
     * Диалог ввода строки поиска
     * @return Строка минимум с одним символом
     */
    protected String askStringForSearch() {
        this.output.print(ENTER_SEARCH);
        return askNotBlankString();
    }

}
