package com.bikmop.petclinic.console;

import com.bikmop.petclinic.Input;

import java.util.Scanner;

/**
 * ���� �� ������� ����������� ��������� ����.
 */
public class ConsoleInput implements Input {
    /**
     * ����� ��� ��������� ������ �� �������
     */
    private final Scanner reader = new Scanner(System.in);


    /**
     * �������� ��������� ������ �� ������������
     * @return ���� ������������
     */
    @Override
    public String next() {
        return reader.nextLine();
    }

    /**
     * ������� ����� ��� ��������� ������ � ��������
     */
    @Override
    public void close() {
        reader.close();
    }
}
