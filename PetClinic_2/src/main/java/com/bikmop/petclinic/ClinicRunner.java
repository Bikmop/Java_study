package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.*;

/**
 * Клас для демонстрации работы клиники из консоли
 */
public class ClinicRunner {
    /**
     * Main
     * @param args Параметры запуска
     */
    public static void main(String[] args) {
        final Clinic clinic = new Clinic(10);
        initialClinicFilling(clinic);
        ConsoleWorker.mainDialog(clinic);
    }


    /**
     * Начальное заполнение клиники клиентами Анна, Иван и Петр
     * @param clinic Клиника
     */
    private static void initialClinicFilling(Clinic clinic) {
        clinic.addClient(createAnna());
        clinic.addClient(createIvan());
        clinic.addClient(createPetr());
    }

    /**
     * Создание клиента Анна
     * @return Клиент Анна
     */
    private static Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

    /**
     * Создание клиента Иван
     * @return Клиент Иван
     */
    private static Client createIvan() {
        Client ivan = new Client("XY 01234567");
        ivan.setFullName("Ivan Petrov");

        return ivan;
    }

    /**
     * Создание клиента Петр
     * @return Клиент Петр
     */
    private static Client createPetr() {
        Client petr = new Client("XY 89012345");
        petr.setFullName("Petr Sidorov");
        petr.addPet(new Cat("Masha"));
        petr.addPet(new Cat("Python"));
        petr.addPet(new Dog("Palkan"));

        return petr;
    }

}
