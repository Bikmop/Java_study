package com.bikmop.petclinic.pet;

/**
 * ����� ��������� ������� ��������
 */
public class PetFactory {
    /**
     * ������� ��������
     * @param type ��� ��������� �� PetType
     * @param name ��� ���������
     * @return �������� ������� ����
     */
    public static Pet createPet(PetType type, String name) {
        switch (type) {
            case CAT:
                return new Cat(name);
            case DOG:
                return new Dog(name);
            case FISH:
                return new Fish(name);
            case BIRD:
                return new Bird(name);
            case REPTILE:
                return new Reptile(name);
            case RODENT:
                return new Rodent(name);
            default:
                return new SomePet(name);
        }
    }

}
