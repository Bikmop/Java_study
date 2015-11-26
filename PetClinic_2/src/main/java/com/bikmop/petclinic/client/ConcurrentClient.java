package com.bikmop.petclinic.client;

/**
 * Класс для тестирования конкурентной(одновременно из разных потоков) работы с клиентом
 */
public class ConcurrentClient {
    /** Клиент */
    public final Client client;

    /**
     * Конструктор
     * @param client Клиент
     */
    public ConcurrentClient(Client client) {
        this.client = client;
    }


    /**
     * Добавление символа к имени клиента.
     * Клиент блокируется на время добавления
     * @param symbol Символ
     */
    public void addSymbolToName(char symbol) {
        synchronized (this.client) {
            this.client.setFullName(this.client.getFullName() + symbol);
        }
    }

    /**
     * Добавление символа к имени клиента.
     * Блокировка не делается
     * @param symbol Символ
     */
    public void addSymbolToNameNotSynch(char symbol) {
        this.client.setFullName(this.client.getFullName() + symbol);
    }
}
