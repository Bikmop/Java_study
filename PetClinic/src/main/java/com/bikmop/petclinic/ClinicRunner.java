package com.bikmop.petclinic;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.Dog;

public class ClinicRunner {

    public static void main(String[] args) {
        final Clinic clinic = new Clinic(10);

//        clinic.addClient(0, new Client("Brown", new Cat("Digy")));
//        clinic.addClient(1, new Client("Nick", new Dog(new Animal("Sparky"))));
//        clinic.addClient(2, new Client("Ann", new CatDog(new Cat("Tom"), new Dog(new Animal("Piccy")))));
    }

}
