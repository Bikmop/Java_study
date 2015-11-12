package com.bikmop.petclinic.console;

import com.bikmop.petclinic.Output;

/**
 * ����� � ������� ����������� ��������� �����
 */
public class ConsoleOutput implements Output {

    /**
     * ������� ��������� � ������� ��� �������� �� ����� ������
     * @param message ���������
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * ������� ��������� � ������� � ��������� �� ����� ������
     * @param message ���������
     */
    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
