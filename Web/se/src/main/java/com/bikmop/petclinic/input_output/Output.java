package com.bikmop.petclinic.input_output;

/**
 * Интерфейс вывода строковых сообщений
 */
public interface Output {
    /**
     * Вывести строку без перехода на следующую
     * @param message Строка сообщения
     */
    void print(String message);

    /**
     * Вывести строку с переходом на следующую
     * @param message Строка сообщения
     */
    void println(String message);
}
