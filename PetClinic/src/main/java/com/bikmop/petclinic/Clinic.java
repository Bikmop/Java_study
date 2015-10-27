package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;

public class Clinic {
    private final Client[] clients;

    public Clinic(final int maxNumberOfClients) {
        this.clients = new Client[maxNumberOfClients];
    }

    public void addClient(int position, Client client) {
        this.clients[position] = client;
    }

    public void addClient(Client client) {
//        this.clients[position] = client;
    }

    public Client[] findClientByPetName(final String name) {
        return null;
    }
}
