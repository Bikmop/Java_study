package com.bikmop.petclinic.client;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.pet.*;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирование конкурентной работы с клиентом
 */
public class ConcurrentClientTest {
    /** Экземпляр клиники */
    private Clinic clinic;


    /**
     * Тестирование синхронизированной версии добавления символов к имени клиента
     * Используется 6 одновременных Thread-ов. 5 из которых меняют имя одного экземпляра клиента
     * Тест проверяется 1000 раз
     *
     * Сбои зафиксировать не удалось
     *
     * @throws Exception
     */
    @Test
    public void testAddSymbolToName() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ":");
            assertEquals(100, oneConcurrentIteration());
        }
    }

    /**
     * Тестирование несинхронизированной версии добавления символов к имени клиента
     * Используется 6 одновременных Thread-ов. 5 из которых меняют имя одного экземпляра клиента
     * Тест проверяется 1000 раз
     *
     * Чаще всего сбой происходит уже на первой итерации.
     * Максимум, что удалось увидеть - почти 200 итераций без сбоев
     *
     * @throws Exception
     */
    @Ignore
    @Test
    public void testAddSymbolToNameNotSynch() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ":");
            assertEquals(100, oneConcurrentIterationNotSynch());
        }
    }




    /**
     * Одна итерация для теста
     * @return Количество символов на которые изменилась длина имени клиента
     */
    public int oneConcurrentIteration() {
        // Создание клиники с клиентами
        final Client anna = createAnna();
        final Client petr = createPetr();
        this.clinic = new Clinic();
        this.clinic.addClient(anna);
        this.clinic.addClient(petr);
        int lengthBefore = anna.getFullName().length();

        // Создание и запуск нитей и ожидание окончания их работы
        Thread[] threads = createThreads(anna, petr);
        runThreads(threads);
        joinThreads(threads);

        // Вывод конечного имени
        System.out.printf("Current Anna's name: %s\n", anna.getFullName());

        // Возврат количества добавленных символов
        return anna.getFullName().length() - lengthBefore;
    }

    /**
     * Создание клиента Анна
     * @return Клиент Анна
     */
    private Client createAnna() {
        Client anna = new Client("Anna Adams", "XX 100200300");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

    /**
     * Создание клиента Петр
     * @return Клиент Петр
     */
    private Client createPetr() {
        Client petr = new Client("XY 89012345");
        petr.setFullName("Petr Sidorov");
        petr.addPet(new Cat("Masha"));
        petr.addPet(new Cat("Python"));
        petr.addPet(new Dog("Palkan"));

        return petr;
    }

    /**
     * Создаются 6 нитей, три из которых напрямую обращаются к объекту Анна - имитация клиентов
     * Две нити имитируют администратора, который выбирает клиента из списка в клинике
     * Последняя работает с другим клиентом
     * @param anna Клиент Анна
     * @param petr Клиент Петр
     * @return Массив нитей
     */
    private Thread[] createThreads(Client anna, Client petr) {
        Thread[] threads = new Thread[6];
        threads[0] = createClientThread(anna, '.');
        threads[1] = createAdminThread(Client.SearchType.NAME_PART, "Anna", '*');
        threads[2] = createClientThread(anna, '-');
        threads[3] = createAdminThread(Client.SearchType.NAME_PART, "Anna", '$');
        threads[4] = createClientThread(anna, '+');
        // Единственный поток касающийся клиента Петр
        // Чтобы убедиться, что он не повлияет на другие(отсутствие у Анны символа #)
        threads[5] = createClientThread(petr, '#');
        return threads;
    }

    /**
     * Запуск всех нитей из массива
     * @param threads Массив нитей
     */
    private void runThreads(Thread[] threads) {
        for (Thread thread : threads)
            thread.start();
    }

    /**
     * Присоединяемся к нитям из массива и ждем окончания их работы
     * @param threads Массив нитей
     */
    private void joinThreads(Thread[] threads) {
        try {
            for (Thread thread : threads)
                thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создание нити имитирующей администратора,
     * который выберет клиента из доступных и добавит к имени 20 раз указанный символ
     * @param type Тип поиска клиента
     * @param search Строка поиска
     * @param symbol Символ для добавления
     * @return Нить администратора
     */
    private Thread createAdminThread(final Client.SearchType type, final String search, final char symbol) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                clinic.selectFirstMatchingClient(type, search);
                ConcurrentClient currentClient = new ConcurrentClient(clinic.getCurrentClient());
                for (int i = 0; i < 20; i++) {
                    currentClient.addSymbolToName(symbol);
                }
            }
        });
    }

    /**
     * Создание нити имитирующей клиента - работа напрямую с указанным клиентом без выбора из клиники
     * Будет поочередно добавлено 20 символов к имени
     * @param client Клиент
     * @param symbol Символ
     * @return Нить клиента
     */
    private Thread createClientThread(final Client client, final char symbol) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrentClient concurrentClient = new ConcurrentClient(client);
                for (int i = 0; i < 20; i++) {
                    concurrentClient.addSymbolToName(symbol);
                }
            }
        });
    }


    /**
     * Одна итерация для теста, но без синхронизации
     * @return Количество символов на которые изменилась длина имени клиента
     */
    public int oneConcurrentIterationNotSynch() {
        final Client anna = createAnna();
        final Client petr = createPetr();
        this.clinic = new Clinic();
        this.clinic.addClient(anna);
        this.clinic.addClient(petr);
        int lengthBefore = anna.getFullName().length();

        Thread[] threads = createThreadsNotSynch(anna, petr);
        runThreads(threads);
        joinThreads(threads);

        System.out.printf("Current Anna's name: %s\n", anna.getFullName());

        return anna.getFullName().length() - lengthBefore;
    }

    /**
     * Создаются 6 нитей, аналогичных методу createThreads, но без синхронизации
     * @param anna Клиент Анна
     * @param petr Клиент Петр
     * @return Массив нитей
     */
    private Thread[] createThreadsNotSynch(Client anna, Client petr) {
        Thread[] threads = new Thread[6];
        threads[0] = createClientThreadNotSynch(anna, '.');
        threads[1] = createAdminThreadNotSynch(Client.SearchType.NAME_PART, "Anna", '*');
        threads[2] = createClientThreadNotSynch(anna, '-');
        threads[3] = createAdminThreadNotSynch(Client.SearchType.NAME_PART, "Anna", '$');
        threads[4] = createClientThreadNotSynch(anna, '+');
        threads[5] = createClientThreadNotSynch(petr, '#');
        return threads;
    }

    /**
     * Создание нити имитирующей администратора аналогочной createAdminThread, но без синхронизации
     * @param type Тип поиска клиента
     * @param search Строка поиска
     * @param symbol Символ для добавления
     * @return Нить администратора
     */
    private Thread createAdminThreadNotSynch(final Client.SearchType type, final String search, final char symbol) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                clinic.selectFirstMatchingClient(type, search);
                ConcurrentClient currentClient = new ConcurrentClient(clinic.getCurrentClient());
                for (int i = 0; i < 20; i++) {
                    currentClient.addSymbolToNameNotSynch(symbol);
                }
            }
        });
    }

    /**
     * Создание нити имитирующей клиента аналогичной createClientThread, но без синхронизации
     * @param client Клиент
     * @param symbol Символ
     * @return Нить клиента
     */
    private Thread createClientThreadNotSynch(final Client client, final char symbol) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrentClient concurrentClient = new ConcurrentClient(client);
                for (int i = 0; i < 20; i++) {
                    concurrentClient.addSymbolToNameNotSynch(symbol);
                }
            }
        });
    }

}