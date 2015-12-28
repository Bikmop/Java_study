package com.bikmop.servlets;

import com.bikmop.petclinic.client.Client;
import com.bikmop.store.ClinicSingleton;

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
    /** Строковые константы */
    private static final String CLINIC_VIEW = "/view/clinic/ClinicView.jsp";
    private static final String ADD_CLIENT = "/view/client/AddClient.jsp";

    /** Экземпляр клиники */
    private final ClinicSingleton CLINIC = ClinicSingleton.getInstance();
    /** Список клиентов для отображения (удовлетворяющий фильтрам поиска) */
    private final List<Client> clientsToShow = new CopyOnWriteArrayList<>();


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
        processAddClient(req, resp);
        processNotAddClient(req, resp);
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
     * Освобождение ресурсов
     */
    @Override
    public void destroy() {
        super.destroy();
        CLINIC.close();
    }


    /**
     * Обработка кнопки добавления клиента
     * @param req Запрос
     */
    private void processAddClient(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("addClient") != null)
            forwardTo(req, resp, ADD_CLIENT);
    }

    /**
     * Обработка ситуаций, если не нажата кнопка добавления клиента
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processNotAddClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("addClient") == null) {
            setDoGetAttributes(req);
            forwardTo(req, resp, CLINIC_VIEW);
        }
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

}
