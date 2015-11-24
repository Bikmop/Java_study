package com.bikmop.equals_hashcode;


/**
 * Класс пользователь.
 * С переопределением метода hashCode(), но без переопределения equals()
 */
public class UserHashcode {
    /** Идентификатор пользователя */
    private String id;
    /** Имя пользователя */
    private String name;
    /** Картинка пользователя */
    private Picture picture;

    /**
     * Конструктор
     * @param id Идентификатор
     * @param name Имя
     * @param picture Картинка
     */
    public UserHashcode(String id, String name, Picture picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }


    /**
     * Получить идентификатор пользователя
     * @return Идентификатор
     */
    public String getId() {
        return id;
    }

    /**
     * Получить имя пользователя
     * @return Имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получить картинку пользователя
     * @return Картинка пользователя
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * Метод нахождения хэш-кода данного объекта
     * @return Хэш-код
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);

        return result;
    }
}
