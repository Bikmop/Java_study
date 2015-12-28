package com.bikmop.store;


import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.PetType;

import java.util.List;

/**
 * Экземпляр-синглтон клиники для веб-приложения
 */
public final class ClinicSingleton implements PetClinic {
    private static final ClinicSingleton INSTANCE = new ClinicSingleton();

    private final PetClinic petClinic = new JdbcClinic();

    private ClinicSingleton() {
    }

    public static ClinicSingleton getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Client> getClients() {
        return petClinic.getClients();
    }

    @Override
    public Client getCurrentClient() {
        return petClinic.getCurrentClient();
    }

    @Override
    public void addClient(Client client) {
        petClinic.addClient(client);
    }

    @Override
    public List<Client> findClients(Client.SearchType type, String toSearch) {
        return petClinic.findClients(type, toSearch);
    }

    @Override
    public void selectFirstMatchingClient(Client.SearchType type, String searchString) {
        petClinic.selectFirstMatchingClient(type, searchString);
    }

    @Override
    public void addPetToCurrentClient(String typeString, String name) {
        petClinic.addPetToCurrentClient(typeString, name);
    }

    @Override
    public void removePetFromCurrentClient(String name) {
        petClinic.removePetFromCurrentClient(name);
    }

    @Override
    public void removeCurrentClient() {
        petClinic.removeCurrentClient();
    }

    @Override
    public void renameCurrentClient(String newName) {
        petClinic.renameCurrentClient(newName);
    }

    @Override
    public boolean isUniqueClientId(String clientId) {
        return petClinic.isUniqueClientId(clientId);
    }

    @Override
    public void close() {
        petClinic.close();
    }
}