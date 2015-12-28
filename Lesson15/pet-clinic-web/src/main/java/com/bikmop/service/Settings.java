package com.bikmop.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс-синглтон для считывания настроек подключения к базе данных
 */
public final class Settings {
    private static final Settings INSTANCE = new Settings();

    private final Properties properties = new Properties();

    /**
     * В конструкторе задается properties-файл настроек
     */
    private Settings() {
        try {
            properties.load(new FileInputStream(
                    this.getClass().getClassLoader().getResource("db.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Получить экземпляр класса
     * @return Экземпляр
     */
    public static synchronized Settings getInstance() {
        return INSTANCE;
    }

    /**
     * Получить значение по ключу из properties-файла настроек
     * @param key Ключ
     * @return Значение
     */
    public String getValue(String key) {
        return this.properties.getProperty(key);
    }
}

