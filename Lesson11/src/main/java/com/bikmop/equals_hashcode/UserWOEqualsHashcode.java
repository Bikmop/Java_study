package com.bikmop.equals_hashcode;

/**
 * Класс пользователь.
 * Без переопределения методов equals() и hashCode()
 */
public class UserWOEqualsHashcode {
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
    public UserWOEqualsHashcode(String id, String name, Picture picture) {
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

}
