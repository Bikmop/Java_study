package com.bikmop.petclinic.pet;

/**
 * ����� ����� ����������� ��������
 */
public class Bird extends Pet {
    /**
     * ������ ���� ���������
     */
    private final static String STRING_TYPE_OF_PET = "Bird";

    /**
     * �����������
     * @param name ��� �����
     */
    public Bird(String name) {
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
