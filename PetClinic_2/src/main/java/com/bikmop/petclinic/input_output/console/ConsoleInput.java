package com.bikmop.petclinic.input_output.console;

import com.bikmop.petclinic.input_output.Input;

import java.util.Scanner;

/**
 * Ввод из консоли, реализующий интерфейс Input.
 */
public class ConsoleInput implements Input {
    /**
     * Ридер для получения данных из консоли
     */
    private final Scanner reader = new Scanner(System.in);


    /**
     * Получить следующую строку от пользователя
     * @return Ввод пользователя
     */
    @Override
    public String next() {
        return reader.nextLine();
    }

    /**
     * Закрыть ридер при окончании работы с консолью
     */
    @Override
    public void close() {
        reader.close();
    }
}
