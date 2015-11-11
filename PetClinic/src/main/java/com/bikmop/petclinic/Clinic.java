package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;

/**
 * ����� ����������� ������� �������� ��������
 */
public class Clinic {
    /**
     * ������ ��������
     */
    private final Client[] clients;
    /**
     * ������� ������ ��� ��������������
     */
    private Client currentClient = null;

    /**
     * �����������
     * @param maxNumberOfClients ������������ ���������� �������� �������
     */
    public Clinic(final int maxNumberOfClients) {
        this.clients = new Client[maxNumberOfClients];
    }


    /**
     * �������� ������ ��������
     * @return ������ ��������
     */
    public Client[] getClients() {
        return this.clients;
    }

    /**
     * �������� �������� �������
     * @return ������� ������
     */
    public Client getCurrentClient() {
        return this.currentClient;
    }

    /**
     * �������� �������
     * @param client ������
     */
    public void addClient(Client client) {
        addClient(findFreePlaceInClientsArray(), client);
    }

    /**
     * ����� �������� �� ���� � ������ ������
     * @param type ��� ������
     * @param toSearch ������ ������
     * @return ������ ��������� ��������
     */
    public Client[] findClients(Client.SearchType type, final String toSearch) {
        Client[] found = new Client[this.clients.length];
        int i = 0;

        for (Client client : this.clients)
            if (client != null && client.hasIn(type, toSearch))
                found[i++] = client;

        return found;
    }

    /**
     * ������� ������� ������� ���������������� ���������� �������
     * @param type ��� ������
     * @param searchString ������ ������
     */
    public void selectFirstMatchingClient(Client.SearchType type, String searchString) {
        boolean found = false;

        for (Client client : this.clients)
            if (client != null && client.hasIn(type, searchString)) {
                selectCurrentClient(client);
                found = true;
                break;
            }

        if (!found)
            selectCurrentClient(null);
    }

    /**
     * ������� �������� ������� �� �������
     * ������� ������ ���������� null
     */
    public void removeCurrentClient() {
        if (this.currentClient != null)
            for (int i = 0; i < this.clients.length; i++)
                if (this.clients[i] == this.currentClient) {
                    this.clients[i] = null;
                    this.currentClient = null;
                    break;
                }
    }

    /**
     * �������������� �������� �������
     * @param newName ����� ���
     */
    public void renameCurrentClient(String newName) {
        if (this.currentClient != null)
            this.currentClient.setFullName(newName);
    }

    /**
     * ��������� ������������ �������������� �������
     * @param clientId Id ��� ��������
     * @return ���������� Id � ������ �������� �������
     */
    public boolean isUniqueClientId(String clientId) {
        boolean uniqueId = true;

        for (Client client : this.clients)
            if (client != null && client.getId().equals(clientId)) {
                uniqueId = false;
                break;
            }

        return uniqueId;
    }


    /**
     * ���������� �������
     * @param position ���������� ����� � �������
     * @param client ������
     */
    private void addClient(int position, Client client) {
        this.clients[position] = client;
    }

    /**
     * ����� ��������� ������ � ������� ��������
     * @return ����� ������
     * @throws FullClientsArrayException ������, ���� ���� ������ �������� ��������
     */
    private int findFreePlaceInClientsArray() throws FullClientsArrayException{
        int i = 0;

        for (Client tmpClient : this.clients) {
            if (tmpClient == null)
                break;

            i++;
        }

        if (i >= this.clients.length)
            throw new FullClientsArrayException();

        return i;
    }

    /**
     * ������� ������� �������
     * @param client ������
     */
    private void selectCurrentClient(Client client) {
        this.currentClient = client;
    }

}
