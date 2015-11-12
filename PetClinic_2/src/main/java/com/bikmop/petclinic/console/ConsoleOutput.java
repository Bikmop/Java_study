package com.bikmop.petclinic.console;

import com.bikmop.petclinic.Output;

/**
 * Вывод в консоль реализующий интерфейс Вывод
 */
public class ConsoleOutput implements Output {

    /**
     * Вывести сообщение в консоль без перехода на новую строку
     * @param message Сообщение
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Вывести сообщение в консоль с переходом на новую строку
     * @param message Сообщение
     */
    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
