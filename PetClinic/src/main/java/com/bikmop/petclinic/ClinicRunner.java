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

        ConsoleWorker.mainDialog(clinic);
//        ConsoleWorker.mainDialog();
/*
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
        clinic.selectClient(NAME_FULL, "Ivan Petrov");
        ConsoleWorker.showCurrentClient(clinic);
        clinic.getCurrentClient().addPet(new Cat("Kotofey"));
        ConsoleWorker.showAllClients(clinic);
        ConsoleWorker.addClientDialog(clinic);
        ConsoleWorker.showAllClients(clinic);
*/

    }

    public static void initialClinicFilling(Clinic clinic) {
        clinic.addClient(createAnna());
        clinic.addClient(createIvan());
        clinic.addClient(createPetr());
    }

    private static Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
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
