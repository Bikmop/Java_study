package com.bikmop.equals_hashcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Тестирование класса User без переопределения методов equals() и hashCode()
 */
public class UserWOEqualsHashcodeTest {
    String id = "123456";
    String name = "User";
    Picture pic1 = new Picture("Picture01");
    Picture pic2 = new Picture("Picture02");
    Picture pic3 = new Picture("Picture01", 100, 100);
    private UserWOEqualsHashcode user1 = new UserWOEqualsHashcode(id, name, pic1);
    private UserWOEqualsHashcode user2 = new UserWOEqualsHashcode(id, name, pic2);
    private UserWOEqualsHashcode user3 = new UserWOEqualsHashcode(id, name, pic3);

    @Test
    public void testEquals() throws Exception {
        // Объекты разные и начинка разная
        assertFalse(user1.equals(user2));
        // Объекты разные, а начинка одинаковая, но не переопределен equals
        assertFalse(user1.equals(user3));
        assertFalse(user1 == user3);
        assertFalse(user1.hashCode() == user3.hashCode());
        // Один объект
        assertTrue(user1.equals(user1));
    }

    /**
     * Тестирование на динамическом списке
     * @throws Exception
     */
    @Test
    public void testOnArrayList() throws Exception {
        List<UserWOEqualsHashcode> users = new ArrayList<>();
        users.add(user1);
        assertTrue(users.contains(user1));

        // user3 копия user1 но не содержится в списке, т.к. объекты разные, а equals не переопределен
        assertFalse(users.contains(user3));

        users.add(user2);
        users.add(user3);

        // ожидаемо для списка, т.к. может содержать много копий даже одного объекта
        assertEquals(3, users.size());
    }

    /**
     * Тестирование на коллекции HashSet
     * @throws Exception
     */
    @Test
    public void testOnHashSet() throws Exception {
        Set<UserWOEqualsHashcode> users = new HashSet<>();
        users.add(user1);
        assertTrue(users.contains(user1));

        // user3 копия user1 но не содержится в Set, т.к. объекты разные, а equals не переопределен
        assertFalse(users.contains(user3));

        users.add(user2);
        users.add(user3);

        // user3 не перезатирает user1, т.к. у них разный hashCode и equals
        assertEquals(3, users.size());
    }

    /**
     * Тестирование на HashMap
     * @throws Exception
     */
    @Test
    public void testOnHashMap() throws Exception {
        Map<UserWOEqualsHashcode, Picture> users = new HashMap<>();
        users.put(user1, user1.getPicture());
        assertTrue(users.containsKey(user1));

        // user3 копия user1 но не содержится в ключах Map, т.к. объекты разные, а equals не переопределен
        assertFalse(users.containsKey(user3));

        // В Map есть только user1, но присутствует value для user3,
        // т.к. у класса Picture переопределены оба метода, хотя объекты pic1 и pic3 разные
        assertTrue(users.containsValue(pic3));
        // Объекты разные, а хэш-коды - одинаковые
        assertFalse(pic1 == pic3);
        assertEquals(pic1.hashCode(), pic3.hashCode());


        users.put(user2, user2.getPicture());
        users.put(user3, user3.getPicture());

        // user3 не перезатирает user1, т.к. у них разный hashCode
        assertEquals(3, users.size());
    }

}