package com.bikmop.petclinic;

import com.bikmop.petclinic.input_output.Input;
import com.bikmop.petclinic.input_output.Output;
import com.bikmop.petclinic.user_actions.*;

import java.util.HashMap;
import java.util.Map;


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
    /** Карта связи основной операции и соответствующей ей реализации действия пользователя */
    private final Map<MainOperation, UserAction> userActions = new HashMap<>();

    /**
     * Строковые константы вывода
     */
    private static final String WELCOME = "********************  WELCOME TO PET CLINIC  ********************";
    private static final String SELECT_MAIN_OPERATION = "Please, select operation.";
    private static final String MAIN_OPERATIONS_LIST = "(1 - Show all clients,  2 - Find clients,  " +
            "3 - Add client,  4 - Edit client,  5 - Quit program): ";
    private static final String SELECT_CORRECT_OPERATION = "Select correct operation! : ";
    private static final String GOOD_BYE = "Good bye.";
    private static final String BLANK_LINE = "";

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

        //Загрузка карты реализациями UserAction
        loadUserAction(new ShowAllClients(this.clinic, this.input, this.output));
        loadUserAction(new FindClients(this.clinic, this.input, this.output));
        loadUserAction(new AddClient(this.clinic, this.input, this.output));
        loadUserAction(new EditClient(this.clinic, this.input, this.output));
    }

    /**
     * Главный диалог с пользователем
     */
    public void mainDialog() {
        for (int i = 0; i < 20; i++) {
            writeMessageLn(BLANK_LINE);
        }
        writeMessageLn(WELCOME);
        dialogWithUserTillQuit();
        writeMessageLn(BLANK_LINE);
        writeMessageLn(GOOD_BYE);
        closeReader();
    }

    /**
     * Добавление в карту реализации UserAction
     * @param userAction Реализация действия пользователя
     */
    private void loadUserAction(UserAction userAction) {
        this.userActions.put(userAction.mainOperation(), userAction);
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
        this.userActions.get(operation).process();
    }

    /**
     * Закрытие input-ридера
     */
    private void closeReader() {
        this.input.close();
    }

}
