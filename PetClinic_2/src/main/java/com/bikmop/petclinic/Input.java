package com.bikmop.petclinic;

/**
 * Интерфейс ввода. Получение строки на запрос
 */
public interface Input {
    /**
     * Получить следующую строку
     * @return Строка ответа
     */
    String next();

    /**
     * Закрытие ресурса чтения строк
     */
    void close();
}
