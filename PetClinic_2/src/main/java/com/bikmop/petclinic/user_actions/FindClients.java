package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.Input;
import com.bikmop.petclinic.Output;
import com.bikmop.petclinic.UserAction;
import com.bikmop.petclinic.client.Client;


/**
 * ����� ��������� ����� �������� �� ������� � ����� ����������� � Output
 */
public class FindClients extends UserAction {
    /**
     * ��������� ��������� ������
     */
    private static final String SEARCH_CLIENT = "Client search...";
    private static final String YES_OR_NO = " (y - for Yes, another - for No): ";
    private static final String ASK_ANOTHER_SEARCH = "Another search?" + YES_OR_NO;
    private static final String SEARCH_RESULT = "Search result:";

    /**
     * �����������
     * @param clinic �������
     * @param input ���������� ���������� �����
     * @param output ���������� ���������� ������
     */
    public FindClients(Clinic clinic, Input input, Output output) {
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
        return MainOperation.FIND;
    }

    /**
     * ��������� �������� ��������������� ���������� UserAction
     */
    @Override
    public void process() {
        String isAnotherSearch = ANSWER_YES;

        this.output.println(SEARCH_CLIENT);

        while (isYes(isAnotherSearch)) {
            Client.SearchType searchType = askSearchType();
            String searchString = askStringForSearch();
            Client[] clients = this.clinic.findClients(searchType, searchString);

            showSearchResult(clients);

            isAnotherSearch = askAnother(ASK_ANOTHER_SEARCH);
            this.output.println(BLANK_LINE);
        }

        this.output.println(BLANK_LINE);
    }

    /**
     * �������� � ������� ��������� ������ ��������
     * @param clients ������ ��������
     */
    private void showSearchResult(Client[] clients) {
        this.output.println(BLANK_LINE);
        this.output.println(SEARCH_RESULT);
        showClients(clients);
    }

}
