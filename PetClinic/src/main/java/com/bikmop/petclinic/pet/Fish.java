package com.bikmop.petclinic.pet;

/**
 * ����� ���� ����������� ��������
 */
public class Fish extends Pet {
    /**
     * ������ ���� ���������
     */
    private final static String STRING_TYPE_OF_PET = "Fish";

    /**
     * �����������
     * @param name ��� ����
     */
    public Fish(String name) {
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
