package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.console.ConsoleInput;
import com.bikmop.petclinic.console.ConsoleOutput;
import com.bikmop.petclinic.pet.*;

/**
 * ���� ��� ������������ ������ ������� �� �������
 */
public class ClinicRunner {
    private final Clinic clinic;
    private final InteractClinic interactClinic;

    public ClinicRunner(Clinic clinic, Input input, Output output) {
        this.clinic = clinic;
        interactClinic = new InteractClinic(clinic, input, output);
    }

    /**
     * Main
     * @param args ��������� �������
     */
    public static void main(String[] args) {
        ClinicRunner runner = new ClinicRunner(new Clinic(10), new ConsoleInput(), new ConsoleOutput());
        runner.initialClinicFilling();
        runner.mainDialog();
    }

    public void mainDialog() {
        interactClinic.mainDialog();
    }

    /**
     * ��������� ���������� ������� ��������� ����, ���� � ����
     */


    public void initialClinicFilling() {
        clinic.addClient(createAnna());
        clinic.addClient(createIvan());
        clinic.addClient(createPetr());
    }

    /**
     * �������� ������� ����
     * @return ������ ����
     */


    private Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

    /**
     * �������� ������� ����
     * @return ������ ����
     */


    private Client createIvan() {
        Client ivan = new Client("XY 01234567");
        ivan.setFullName("Ivan Petrov");

        return ivan;
    }

    /**
     * �������� ������� ����
     * @return ������ ����
     */


    private Client createPetr() {
        Client petr = new Client("XY 89012345");
        petr.setFullName("Petr Sidorov");
        petr.addPet(new Cat("Masha"));
        petr.addPet(new Cat("Python"));
        petr.addPet(new Dog("Palkan"));

        return petr;
    }

}
