package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.*;

import java.util.ArrayList;
import java.util.List;

import static com.bikmop.petclinic.client.Client.SearchType.*;

public class ClinicRunner {

    public static void main(String[] args) {
        final Clinic clinic = new Clinic(10);
        initialClinicFilling(clinic);
        ConsoleWorker.showAllClients(clinic);
        clinic.selectClient(PETS_NAME, "Snail");
        ConsoleWorker.showCurrentClient(clinic);
        clinic.renameCurrentClient("Anna Sidorova");
        ConsoleWorker.showAllClients(clinic);
        clinic.getCurrentClient().selectPetByFullName("Kesha");
        clinic.getCurrentClient().renameCurrentPet("Popka durak");
        ConsoleWorker.showCurrentClient(clinic);
        clinic.getCurrentClient().selectPetByFullName("Python");
        clinic.getCurrentClient().removeCurrentPet();
        ConsoleWorker.showCurrentClient(clinic);
        ConsoleWorker.showAllClients(clinic);

//        clinic.addClient(0, new Client("Brown", new Cat("Digy")));
//        clinic.addClient(1, new Client("Nick", new Dog(new Animal("Sparky"))));
//        clinic.addClient(2, new Client("Ann", new CatDog(new Cat("Tom"), new Dog(new Animal("Piccy")))));
    }

    public static void initialClinicFilling(Clinic clinic) {
        clinic.addClient(createAnna());
        clinic.addClient(createIvan());
        clinic.addClient(createPetr());
    }

    private static Client createAnna() {
        List<Pet> annaPets = new ArrayList<Pet>();
        annaPets.add(new Bird("Kesha"));
        annaPets.add(new Rodent("Mickey"));
        annaPets.add(new Reptile("Python"));
        annaPets.add(new SomePet("Snail"));

        return new Client("Anna Ivanova", "XX 33335789", annaPets);
    }

    private static Client createIvan() {
        Client ivan = new Client("XY 01234567");
        ivan.setFullName("Ivan Petrov");

        return ivan;
    }

    private static Client createPetr() {
        Client petr = new Client("XY 89012345");
        petr.setFullName("Petr Sidorov");
        petr.addPet(new Cat("Masha"));
        petr.addPet(new Cat("Python"));
        petr.addPet(new Dog("Palkan"));

        return petr;
    }

}
