package com.bikmop.petclinic.pet;

/**
 * ����� ��� ����������� ��������
 */
public class Cat extends Pet {
    /**
     * ������ ���� ���������
     */
    private final static String STRING_TYPE_OF_PET = "Cat";

    /**
     * �����������
     * @param name ��� ����
     */
    public Cat(String name) {
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
