package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.input_output.Input;
import com.bikmop.petclinic.input_output.Output;
import com.bikmop.petclinic.client.Client;

import java.util.List;


/**
 * Класс реализует поиск клиентов по запросу и вывод результатов в Output
 */
public class FindClients extends UserAction {
    /**
     * Строковые константы вывода
     */
    private static final String SEARCH_CLIENT = "Client search...";
    private static final String YES_OR_NO = " (y - for Yes, another - for No): ";
    private static final String ASK_ANOTHER_SEARCH = "Another search?" + YES_OR_NO;
    private static final String SEARCH_RESULT = "Search result:";

    /**
     * Конструктор
     * @param clinic Клиника
     * @param input Реализация интерфейса ввода
     * @param output Реализация интерфейса вывода
     */
    public FindClients(Clinic clinic, Input input, Output output) {
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
        return MainOperation.FIND;
    }

    /**
     * Выполнить действие соответствующее реализации UserAction
     */
    @Override
    public void process() {
        String isAnotherSearch = ANSWER_YES;

        this.output.println(SEARCH_CLIENT);

        while (isYes(isAnotherSearch)) {
            Client.SearchType searchType = askSearchType();
            String searchString = askStringForSearch();
            List<Client> clients = this.clinic.findClients(searchType, searchString);

            showSearchResult(clients);

            isAnotherSearch = askAnother(ASK_ANOTHER_SEARCH);
            this.output.println(BLANK_LINE);
        }

        this.output.println(BLANK_LINE);
    }

    /**
     * Показать в консоль результат поиска клиентов
     * @param clients Массив клиентов
     */
    private void showSearchResult(List<Client> clients) {
        this.output.println(BLANK_LINE);
        this.output.println(SEARCH_RESULT);
        showClients(clients);
    }

}
