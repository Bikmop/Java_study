package com.bikmop.petclinic.input_output;

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
