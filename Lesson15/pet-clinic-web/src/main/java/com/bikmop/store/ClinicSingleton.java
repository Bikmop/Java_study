package com.bikmop.store;


import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.PetType;

import java.util.List;

/**
 * Экземпляр-синглтон клиники для использования в веб-приложении.
 * В строке определения клиники ( "private final PetClinic petClinic =" )
 * можно задать любую реализацию интерфейса PetClinic.
 * Сейчас реализованы две клиники:
 *  1) SeClinic - клиника на основе модели из пакета SE. Все состояния клиники хранятся в памяти JVM.
 *  2) JdbcClinic - клиника на основе базы данных. Все данные хранятся в базе.
 */
public final class ClinicSingleton implements PetClinic {
    /** Инициализация единственного экземпляра */
    private static final ClinicSingleton INSTANCE = new ClinicSingleton();

    /**
     * Выбор реализации клиники
     */
    private final PetClinic petClinic = new JdbcClinic();

    private ClinicSingleton() {
    }

    /**
     * Получить экземпляр
     * @return Экземпляр
     */
    public static ClinicSingleton getInstance() {
        return INSTANCE;
    }


    /**
     * Получить список клиентов
     * @return Список клиентов
     */
    @Override
    public List<Client> getClients() {
        return petClinic.getClients();
    }

    /**
     * Получить текущего клиента
     * @return Текущий клиент
     */
    @Override
    public Client getCurrentClient() {
        return petClinic.getCurrentClient();
    }

    /**
     * Добавить клиента
     * @param client Клиент
     */
    @Override
    public void addClient(Client client) {
        petClinic.addClient(client);
    }

    /**
     * Найти клиентов по типу и строке поиска
     * @param type Тип поиска
     * @param toSearch Строка поиска
     * @return Список найденных клиентов
     */
    @Override
    public List<Client> findClients(Client.SearchType type, String toSearch) {
        return petClinic.findClients(type, toSearch);
    }

    /**
     * Выбрать первого клиента удовлетворяющего поисковому запросу
     * @param type Тип поиска
     * @param searchString Строка поиска
     */
    @Override
    public void selectFirstMatchingClient(Client.SearchType type, String searchString) {
        petClinic.selectFirstMatchingClient(type, searchString);
    }

    /**
     * Добавить животное текущему клиенту
     * @param typeString Тип
     * @param name Имя
     */
    @Override
    public void addPetToCurrentClient(String typeString, String name) {
        petClinic.addPetToCurrentClient(typeString, name);
    }

    /**
     * Удалить животное у текущего клиента
     * @param name Имя животного
     */
    @Override
    public void removePetFromCurrentClient(String name) {
        petClinic.removePetFromCurrentClient(name);
    }

    /**
     * Удалить текущего клиента из списка
     * Текущий клиент становится null
     */
    @Override
    public void removeCurrentClient() {
        petClinic.removeCurrentClient();
    }

    /**
     * Переименование текущего клиента
     * @param newName Новое имя
     */
    @Override
    public void renameCurrentClient(String newName) {
        petClinic.renameCurrentClient(newName);
    }

    /**
     * Проверить уникальность идентификатора клиента
     * @param clientId Id для проверки
     * @return Отсутствие Id у других клиентов клиники
     */
    @Override
    public boolean isUniqueClientId(String clientId) {
        return petClinic.isUniqueClientId(clientId);
    }

    /**
     * Освобождение ресурсов перед закрытием клиники.
     */
    @Override
    public void close() {
        petClinic.close();
    }
}