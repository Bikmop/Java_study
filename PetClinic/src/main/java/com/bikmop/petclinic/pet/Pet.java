package com.bikmop.petclinic.pet;

/**
 * ����������� ����� ��������
 */
public abstract class Pet {
    /**
     * ������ �������������� ��������� � ������
     */
    private static final String PET_TO_STRING_FORMAT = "%s '%s'";
    /**
     * ��� ���������
     */
    private String name;

    /**
     * �����������
     * @param name ��� ���������
     */
    public Pet(final String name) {
        this.name = name;
    }

    /**
     * �������� ��� ���������
     * @return ��� ���������
     */
    public String getName() {
        return this.name;
    }

    /**
     * ���������� ��� ���������
     * @param name ����� ���
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * �������� ������ - ��� ���������
     * @return ��� ���������
     */
    public abstract String getStringPetType();

    /**
     * ��������� ������������� ���������
     * @return ��� � ���. ������: "Cat 'Tom'"
     */
    @Override
    public String toString() {
        return String.format(PET_TO_STRING_FORMAT, getStringPetType(), this.name);
    }

    /**
     * �������� ����� ���������
     * @param searchName ��� ��� ��������
     * @return ����� �� �����
     */
    public boolean isNameEquals(String searchName) {
        return this.name.equals(searchName);
    }

    /**
     * �������� ������� ����� �����(� ����� ��������) � ����� ���������
     * @param searchPartName ����� �����
     * @return ����������� ����� �����
     */
    public boolean hasInName(String searchPartName) {
        return hasInString(this.name, searchPartName);
    }

    /**
     * �������� ������� ��������� � ������
     * @param mainString ������ � ������� ������������ �����
     * @param searchString ��������� ������
     * @return ����������� ���������
     */
    private static boolean hasInString(final String mainString, final String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }
}
