package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;

/**
 * Класс реализующий клинику домашних животных
 */
public class Clinic {
    /**
     * Массив клиентов
     */
    private final Client[] clients;
    /**
     * Текущий клиент для редактирования
     */
    private Client currentClient = null;

    /**
     * Конструктор
     * @param maxNumberOfClients Максимальное количество клиентов клиники
     */
    public Clinic(final int maxNumberOfClients) {
        this.clients = new Client[maxNumberOfClients];
    }


    /**
     * Получить массив клиентов
     * @return Массив клиентов
     */
    public Client[] getClients() {
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
        addClient(findFreePlaceInClientsArray(), client);
    }

    /**
     * Найти клиентов по типу и строке поиска
     * @param type Тип поиска
     * @param toSearch Строка поиска
     * @return Массив найденных клиентов
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
     * Удалить текущего клиента из массива
     * Текущий клиент становится null
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
     * Добавление клиента
     * @param position Порядковый номер в массиве
     * @param client Клиент
     */
    private void addClient(int position, Client client) {
        this.clients[position] = client;
    }

    /**
     * Поиск свободной ячейки в массиве клиентов
     * @return Номер ячейки
     * @throws FullClientsArrayException Падает, если весь массив клиентов заполнен
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
     * Выбрать клиента текущим
     * @param client Клиент
     */
    private void selectCurrentClient(Client client) {
        this.currentClient = client;
    }

}
