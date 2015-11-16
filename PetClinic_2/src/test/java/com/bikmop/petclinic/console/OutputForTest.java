package com.bikmop.petclinic.console;

import com.bikmop.petclinic.Output;

public class OutputForTest implements Output {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void print(String message) {
        this.sb.append(message);
    }

    @Override
    public void println(String message) {
        this.sb.append(message).append(System.lineSeparator());
    }

    public String getOutput() {
        return sb.toString();
    }
}
