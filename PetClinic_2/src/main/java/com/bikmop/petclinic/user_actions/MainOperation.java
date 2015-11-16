package com.bikmop.petclinic.user_actions;


/**
 * Список основных операций пользователя
 */
public enum MainOperation {
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
    public static MainOperation getMainOperationByString(String operationString) {
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
