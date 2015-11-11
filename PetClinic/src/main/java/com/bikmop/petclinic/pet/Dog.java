package com.bikmop.petclinic.pet;

/**
 * ����� ������ ����������� ��������
 */
public class Dog extends Pet{
    /**
     * ������ ���� ���������
     */
    private final static String STRING_TYPE_OF_PET = "Dog";

    /**
     * �����������
     * @param name ��� ������
     */
    public Dog(String name) {
        super(name);
    }

    /**
     * ���������� ��������� ���
     * @return ��������� ���
     */
    @Override
    public String getStringPetType() {
        return STRING_TYPE_OF_PET;
    }
}
