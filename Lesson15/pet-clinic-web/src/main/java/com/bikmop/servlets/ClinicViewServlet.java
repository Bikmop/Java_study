package com.bikmop.servlets;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.*;
import com.bikmop.store.PetClinic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Сервлет отображения клиники домашних животных
 */
public class ClinicViewServlet extends HttpServlet {
    /** Строковая константа */
    private static final String CLINIC_VIEW = "/view/clinic/ClinicView.jsp";

    /** Экземпляр клиники */
    private final PetClinic CLINIC = PetClinic.getInstance();
    /** Список клиентов для отображения (удовлетворяющий фильтрам поиска) */
    private final List<Client> clientsToShow = new CopyOnWriteArrayList<>();

    /**
     * Добавление нескольких клиентов в клинику
     */
    {
        initialClinicFilling();
    }

    /**
     * Обработка get-запросов
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        setDoGetAttributes(req);
        forwardTo(req, resp, CLINIC_VIEW);
    }

    /**
     * Обработка post-запросов
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        processSearchFilters(req);
        fillPreviousAttributes(req);

        doGet(req, resp);
    }


    /**
     * Установка атрибутов метода doGet
     * @param req Запрос
     */
    private void setDoGetAttributes(HttpServletRequest req) {
        req.setAttribute("clients", clientsToShow);
        req.setAttribute("showClients", req.getParameter("search") != null);
    }

    /**
     * Перенаправление на указанный адрес
     * @param req Запрос
     * @param resp Ответ
     * @param path Адрес
     * @throws ServletException
     * @throws IOException
     */
    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

    /**
     * Обработка фильтров поиска заданных пользователем на странице
     * @param req Запрос
     */
    private void processSearchFilters(HttpServletRequest req) {
        if (req.getParameter("search") != null) {
            clientsToShow.clear();
            clientsToShow.addAll(CLINIC.getClients());

            filterByName(req);
            filterById(req);
            filterByPetName(req);
        }
    }

    /**
     * Фильтрация по имени клиента
     * @param req Запрос
     */
    private void filterByName(HttpServletRequest req) {
        String searchName = req.getParameter("name");
        Client.SearchType searchType;
        if (!"".equals(searchName)) {
            if (req.getParameter("nameFull") != null)
                searchType = Client.SearchType.NAME_FULL;
            else
                searchType = Client.SearchType.NAME_PART;

            clientsToShow.retainAll(CLINIC.findClients(searchType, searchName));
        }
    }

    /**
     * Фильтрация по id клиента
     * @param req Запрос
     */
    private void filterById(HttpServletRequest req) {
        String searchId = req.getParameter("id");

        Client.SearchType searchType;
        if (!"".equals(searchId)) {
            if (req.getParameter("idFull") != null)
                searchType = Client.SearchType.ID_FULL;
            else
                searchType = Client.SearchType.ID_PART;

            clientsToShow.retainAll(CLINIC.findClients(searchType, searchId));
        }
    }

    /**
     * Фильтрация по имени животного
     * @param req Запрос
     */
    private void filterByPetName(HttpServletRequest req) {
        String searchPetName = req.getParameter("petName");
        Client.SearchType searchType;
        if (!"".equals(searchPetName)) {
            searchType = Client.SearchType.PETS_NAME;
            clientsToShow.retainAll(CLINIC.findClients(searchType, searchPetName));
        }
    }

    /**
     * Передача тех же значений полей на странице
     * @param req Запрос
     */
    private void fillPreviousAttributes(HttpServletRequest req) {
        if (req.getParameter("clear") == null) {
            req.setAttribute("idValue", req.getParameter("id"));
            req.setAttribute("idFullValue", req.getParameter("idFull"));
            req.setAttribute("nameValue", req.getParameter("name"));
            req.setAttribute("nameFullValue", req.getParameter("nameFull"));
            req.setAttribute("petNameValue", req.getParameter("petName"));
        }
    }


// Методы предварительного заполнения клиники. Для тестов, чтобы меньше вводить через сайт.
    /**
     * Заполнение клиники клиентами Анна, Иван и Петр
     */
    private void initialClinicFilling() {
        CLINIC.addClient(createAnna());
        CLINIC.addClient(createIvan());
        CLINIC.addClient(createPetr());
    }

    /**
     * Создание клиента Анна
     * @return Клиент Анна
     */
    private Client createAnna() {
        Client anna = new Client("Anna Ivanova", "XX 33335789");
        anna.addPet(new Bird("Kesha"));
        anna.addPet(new Rodent("Mickey"));
        anna.addPet(new Reptile("Python"));
        anna.addPet(new SomePet("Snail"));

        return anna;
    }

    /**
     * Создание клиента Иван
     * @return Клиент Иван
     */
    private Client createIvan() {
        Client ivan = new Client("XY 01234567");
        ivan.setFullName("Ivan Petrov");

        return ivan;
    }

    /**
     * Создание клиента Петр
     * @return Клиент Петр
     */
    private Client createPetr() {
        Client petr = new Client("XY 89012345");
        petr.setFullName("Petr Sidorov");
        petr.addPet(new Cat("Masha"));
        petr.addPet(new Cat("Python"));
        petr.addPet(new Dog("Palkan"));

        return petr;
    }
}
