package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;

public class Clinic {
    private final Client[] clients;
    private Client currentClient = null;

    public Clinic(final int maxNumberOfClients) {
        this.clients = new Client[maxNumberOfClients];
    }

    public Client[] getClients() {
        return this.clients;
    }

    public void addClient(Client client) {
        addClient(findFreePlaceInClientsArray(), client);
    }

    public void selectClient(Client.SearchType type, String selectString) {
        for (Client client : this.clients)
            if (client != null && client.hasIn(type, selectString))
                selectCurrentClient(client);
    }

    public Client getCurrentClient() {
        return this.currentClient;
    }

    private void selectCurrentClient(Client client) {
        this.currentClient = client;
    }

    public void removeCurrentClient() {
        if (this.currentClient != null)
            for (int i = 0; i < this.clients.length; i++)
                if (this.clients[i] == this.currentClient) {
                    this.clients[i] = null;
                    this.currentClient = null;
                    break;
                }
    }

    public void renameCurrentClient(String newName) {
        if (this.currentClient != null)
            this.currentClient.setFullName(newName);
    }

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

    private void addClient(int position, Client client) {
        this.clients[position] = client;
    }

    public Client[] findClients(Client.SearchType type, final String toSearch) {
        Client[] found = new Client[this.clients.length];
        int i = 0;

        for (Client client : this.clients)
            if (client != null && client.hasIn(type, toSearch))
                found[i++] = client;

        return found;
    }

}
