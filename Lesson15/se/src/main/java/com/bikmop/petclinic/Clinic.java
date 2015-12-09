package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.lists.ArrayListForClinic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс реализующий клинику домашних животных
 */
public class Clinic {
    /**
     * Список клиентов
     */
    private final List<Client> clients;
    /**
     * Текущий клиент для редактирования
     */
    private Client currentClient = null;

    /**
     * Конструктор
     */
    public Clinic() {
        this.clients = new CopyOnWriteArrayList<>();
    }


    /**
     * Получить список клиентов
     * @return Список клиентов
     */
    public List<Client> getClients() {
        return this.clients;
    }

    /**
     * Получить текущего клиента
     * @return Текущий клиент
     */
    public Client getCurrentClient() {
        return this.currentClient;
    }

    /**
     * Добавить клиента
     * @param client Клиент
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Найти клиентов по типу и строке поиска
     * @param type Тип поиска
     * @param toSearch Строка поиска
     * @return Список найденных клиентов
     */
    public List<Client> findClients(Client.SearchType type, final String toSearch) {
        List<Client> found = new CopyOnWriteArrayList<>();

        for (Client client : this.clients)
            if (client != null && client.hasIn(type, toSearch))
                found.add(client);

        return found;
    }

    /**
     * Выбрать первого клиента удовлетворяющего поисковому запросу
     * @param type Тип поиска
     * @param searchString Строка поиска
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
     * Удалить текущего клиента из списка
     * Текущий клиент становится null
     */
    public void removeCurrentClient() {
        if (this.currentClient != null)
            if (this.clients.remove(this.currentClient))
                this.currentClient = null;
    }

    /**
     * Переименование текущего клиента
     * @param newName Новое имя
     */
    public void renameCurrentClient(String newName) {
        if (this.currentClient != null)
            this.currentClient.setFullName(newName);
    }

    /**
     * Проверить уникальность идентификатора клиента
     * @param clientId Id для проверки
     * @return Отсутствие Id у других клиентов клиники
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
     * Выбрать клиента текущим
     * @param client Клиент
     */
    private void selectCurrentClient(Client client) {
        this.currentClient = client;
    }

}
