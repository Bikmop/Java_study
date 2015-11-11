package com.bikmop.petclinic.pet;

/**
 * ����� ������ ����������� ��������
 */
public class Rodent extends Pet {
    /**
     * ������ ���� ���������
     */
    private final static String STRING_TYPE_OF_PET = "Rodent";

    /**
     * �����������
     * @param name ��� �������
     */
    public Rodent(String name) {
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
