package com.bikmop.equals_hashcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Тестирование класса User с переопределением только метода hashCode()
 */
public class UserHashcodeTest {
    String id = "123456";
    String name = "User";
    Picture pic1 = new Picture("Picture01");
    Picture pic2 = new Picture("Picture02");
    Picture pic3 = new Picture("Picture01", 100, 100);
    private UserHashcode user1 = new UserHashcode(id, name, pic1);
    private UserHashcode user2 = new UserHashcode(id, name, pic2);
    private UserHashcode user3 = new UserHashcode(id, name, pic3);

    @Test
    public void testEquals() throws Exception {
        // Объекты разные и начинка разная
        assertFalse(user1.equals(user2));
        // Объекты разные, а начинка одинаковая. Хотя hashCode и равен у обоих объектов, но
        // на equals() это не влияет, т.к. он не переопределен.
        assertFalse(user1.equals(user3));
        assertFalse(user1 == user3);
        assertTrue(user1.hashCode() == user3.hashCode());
        // Один объект
        assertTrue(user1.equals(user1));
    }

    /**
     * Тестирование на динамическом списке
     * @throws Exception
     */
    @Test
    public void testOnArrayList() throws Exception {
        List<UserHashcode> users = new ArrayList<>();
        users.add(user1);
        assertTrue(users.contains(user1));

        // user3 копия user1, но не содержится в списке. equals не переопределен, поэтому False
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
        Set<UserHashcode> users = new HashSet<>();
        users.add(user1);
        assertTrue(users.contains(user1));

        // user3 копия user1 но не содержится в Set. Поиск идет по equals, а он не переопределен
        assertFalse(users.contains(user3));

        users.add(user2);
        users.add(user3);

        // user3 не перезатирает user1, т.к. не только должны совпадать hashCode, но и equals
        // Совпадение hashCode не гарантирует равенство equals, поэтому и нет перезатирания
        assertEquals(3, users.size());

    }

    /**
     * Тестирование на HashMap
     * @throws Exception
     */
    @Test
    public void testOnHashMap() throws Exception {
        Map<UserHashcode, Picture> users = new HashMap<>();
        users.put(user1, user1.getPicture());
        assertTrue(users.containsKey(user1));

        // user3 копия user1 но не содержится в ключах Map. Поиск идет по equals, а он не переопределен
        assertFalse(users.containsKey(user3));

        // В Map есть только user1, но присутствует value для user3,
        // т.к. у класса Picture переопределены оба метода, хотя объекты pic1 и pic3 разные по ссылкам
        assertTrue(users.containsValue(pic3));
        // Объекты разные, а хэш-коды - одинаковые
        assertFalse(pic1 == pic3);
        assertEquals(pic1.hashCode(), pic3.hashCode());


        users.put(user2, user2.getPicture());
        users.put(user3, user3.getPicture());

        // user3 не перезатирает user1, т.к. ключи не равны по equals, а только по hashCode
        assertEquals(3, users.size());
    }
}