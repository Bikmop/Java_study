package com.bikmop.store;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Pet;
import com.bikmop.petclinic.pet.PetType;

import java.util.List;

/**
 * Интерфейс, который должна реализовывать клиника домашних животных
 */
public interface PetClinic {

    /**
     * Получить список клиентов
     * @return Список клиентов
     */
    List<Client> getClients();

    /**
     * Получить текущего клиента
     * @return Текущий клиент
     */
    Client getCurrentClient();

    /**
     * Добавить клиента
     * @param client Клиент
     */
    void addClient(Client client);

    /**
     * Найти клиентов по типу и строке поиска
     * @param type Тип поиска
     * @param toSearch Строка поиска
     * @return Список найденных клиентов
     */
    List<Client> findClients(Client.SearchType type, final String toSearch);

    /**
     * Выбрать первого клиента удовлетворяющего поисковому запросу
     * @param type Тип поиска
     * @param searchString Строка поиска
     */
    void selectFirstMatchingClient(Client.SearchType type, String searchString);

    /**
     * Удалить текущего клиента из списка
     * Текущий клиент становится null
     */
    void removeCurrentClient();

    /**
     * Переименование текущего клиента
     * @param newName Новое имя
     */
    void renameCurrentClient(String newName);

    /**
     * Проверить уникальность идентификатора клиента
     * @param clientId Id для проверки
     * @return Отсутствие Id у других клиентов клиники
     */
    boolean isUniqueClientId(String clientId);

    /**
     * Добавить животное текущему клиенту
     * @param typeString Тип
     * @param name Имя
     */
    void addPetToCurrentClient(String typeString, String name);

    /**
     * Удалить животное у текущего клиента
     * @param name Имя животного
     */
    void removePetFromCurrentClient(String name);

    /**
     * Освобождение ресурсов перед закрытием клиники.
     */
    void close();
}
