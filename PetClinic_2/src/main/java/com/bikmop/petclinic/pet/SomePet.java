package com.bikmop.petclinic.pet;

/**
 * ����� ��� ��������� ������� ����(�� �� ���������� ������)
 */
public class SomePet extends Pet {
    /**
     * ������ ���� ���������
     */
    private final static String STRING_TYPE_OF_PET = "Pet";

    /**
     * �����������
     * @param name ��� ���������
     */
    public SomePet(String name) {
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
