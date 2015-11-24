package com.bikmop.equals_hashcode;


/**
 * Класс пользователь.
 * С переопределением метода equals(), но без переопределения hashCode()
 */
public class UserEquals {
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
    public UserEquals(String id, String name, Picture picture) {
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

        UserEquals that = (UserEquals) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;

        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return !(picture != null ? !picture.equals(that.picture) : that.picture != null);

    }

}
