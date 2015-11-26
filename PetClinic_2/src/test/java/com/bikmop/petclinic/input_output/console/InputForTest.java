package com.bikmop.petclinic.input_output.console;

import com.bikmop.petclinic.input_output.Input;

import java.util.Iterator;

public class InputForTest implements Input {
    private final Iterator<String> answers;

    public InputForTest(Iterator<String> answers) {
        this.answers = answers;
    }

    @Override
    public String next() {
        return this.answers.next();
    }

    @Override
    public void close() {
    }
}
