package com.bikmop.equals_hashcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Тестирование класса User с переопределением методов hashCode() и equals()
 */
public class UserEqualsHashcodeTest {
    String id = "123456";
    String name = "User";
    Picture pic1 = new Picture("Picture01");
    Picture pic2 = new Picture("Picture02");
    Picture pic3 = new Picture("Picture01", 100, 100);
    private UserEqualsHashcode user1 = new UserEqualsHashcode(id, name, pic1);
    private UserEqualsHashcode user2 = new UserEqualsHashcode(id, name, pic2);
    private UserEqualsHashcode user3 = new UserEqualsHashcode(id, name, pic3);

    @Test
    public void testEquals() throws Exception {
        // Объекты разные и начинка разная
        assertFalse(user1.equals(user2));
        // Объекты разные, а начинка одинаковая. hashCode и equals() равны, а ссылки - нет.
        assertTrue(user1.equals(user3));
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
        List<UserEqualsHashcode> users = new ArrayList<>();
        users.add(user1);
        assertTrue(users.contains(user1));

        // user3 копия user1 и содержится в списке. Поиск по equals, поэтому True
        assertTrue(users.contains(user3));

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
        Set<UserEqualsHashcode> users = new HashSet<>();
        users.add(user1);
        assertTrue(users.contains(user1));

        // user3 копия user1 и содержится в Set. Поиск идет по equals
        assertTrue(users.contains(user3));

        users.add(user2);
        users.add(user3);

        // user3 перезатирает user1, т.к. совпадают и hashCode, и equals, хотя ссылки разные
        assertEquals(2, users.size());

    }

    /**
     * Тестирование на HashMap
     * @throws Exception
     */
    @Test
    public void testOnHashMap() throws Exception {
        Map<UserEqualsHashcode, Picture> users = new HashMap<>();
        users.put(user1, user1.getPicture());
        assertTrue(users.containsKey(user1));

        // user3 копия user1 и содержится в ключах Map. Поиск идет по equals
        assertTrue(users.containsKey(user3));

        // В Map есть только user1, но присутствует value для user3,
        // т.к. у класса Picture переопределены оба метода, хотя объекты pic1 и pic3 разные по ссылкам
        assertTrue(users.containsValue(pic3));
        // Объекты разные, а хэш-коды - одинаковые
        assertFalse(pic1 == pic3);
        assertEquals(pic1.hashCode(), pic3.hashCode());


        users.put(user2, user2.getPicture());
        users.put(user3, user3.getPicture());

        // user3 перезатирает user1, т.к. ключи равны по equals и по hashCode
        assertEquals(2, users.size());
    }

}