package com.bikmop.petclinic.user_actions;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.input_output.Input;
import com.bikmop.petclinic.input_output.Output;


/**
 * Класс реализует отображение всех клиентов клиники в интерфейс Output
 */
public class ShowAllClients extends UserAction {
    /**
     * Строковые константы вывода
     */
    private static final String CLINICS_CLIENTS = "OUR CLIENTS:";


    /**
     * Конструктор
     * @param clinic Клиника
     * @param input Реализация интерфейса ввода
     * @param output Реализация интерфейса вывода
     */
    public ShowAllClients(Clinic clinic, Input input, Output output) {
        this.clinic = clinic;
        this.output = output;
    }


    /**
     * Основная операция соответствующая данной реализации UserAction
     * @return Основная операция
     */
    @Override
    public MainOperation mainOperation() {
        return MainOperation.SHOW_ALL_CLIENTS;
    }

    /**
     * Выполнить действие соответствующее реализации UserAction
     */
    @Override
    public void process() {
        this.output.println(CLINICS_CLIENTS);
        showClients(this.clinic.getClients());
        this.output.println(BLANK_LINE);
    }

}
