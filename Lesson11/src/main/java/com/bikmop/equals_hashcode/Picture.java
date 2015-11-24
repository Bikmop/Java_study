package com.bikmop.equals_hashcode;


/**
 * Класс реализующий картинку пользователя
 */
public class Picture {
    /** Описание */
    String description;
    /** Ширина */
    int width;
    /** Высота */
    int height;

    /**
     * Конструктор
     * @param description Описание
     * @param width Ширина
     * @param height Высота
     */
    public Picture(String description, int width, int height) {
        this.description = description;
        this.width = width;
        this.height = height;
    }

    /**
     * Конструктор
     * @param description Описание
     */
    public Picture(String description) {
        this(description, 100, 100);
    }


    /**
     * Получить описание картинки
     * @return Описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получить ширину картинки
     * @return Ширина
     */
    public int getWidth() {
        return width;
    }

    /**
     * Получить высоту картинки
     * @return Высота
     */
    public int getHeight() {
        return height;
    }

    /**
     * Метод для сравнения объекта с данным
     * @param o Объект
     * @return Равность
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Picture picture = (Picture) o;

        if (width != picture.width)
            return false;

        if (height != picture.height)
            return false;

        return !(description != null ? !description.equals(picture.description) : picture.description != null);

    }

    /**
     * Метод нахождения хэш-кода данного объекта
     * @return Хэш-код
     */
    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}