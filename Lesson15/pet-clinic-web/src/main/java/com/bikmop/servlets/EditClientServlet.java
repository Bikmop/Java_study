package com.bikmop.servlets;


import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;
import com.bikmop.store.ClinicSingleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обработки редактирования клиента
 */
public class EditClientServlet extends HttpServlet {
    /** Строковые константы */
    private static final String EDIT_CLIENT = "/view/client/EditClient.jsp";
    private static final String CLINIC_VIEW = "/view/clinic/ClinicView.jsp";
    private static final String CLIENT_NAME_CHANGED = " - имя клиента изменено!";

    /** Экземпляр клиники */
    private final ClinicSingleton CLINIC = ClinicSingleton.getInstance();


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
        setClientToEdit(req);
        forwardTo(req, resp, EDIT_CLIENT);
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
        processUserActions(req, resp);
        forwardDependsOfClientState(req, resp);

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
     * Установка клиента для редактирования
     * @param req Запрос
     */
    private void setClientToEdit(HttpServletRequest req) {
        CLINIC.selectFirstMatchingClient(Client.SearchType.ID_FULL, req.getParameter("id"));
        req.setAttribute("client", CLINIC.getCurrentClient());
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
     * Обработка действий пользователя
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processUserActions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUpdateClient(req);
        processAddPet(req);
        processRemoveClient(req, resp);
    }

    /**
     * Обработка обновления данных клиента
     * @param req Запрос
     */
    private void processUpdateClient(HttpServletRequest req) {
        if (req.getParameter("update") != null) {
            String name = req.getParameter("name");

            if (!CLINIC.getCurrentClient().getFullName().equals(name)) {
                CLINIC.renameCurrentClient(name);
                req.setAttribute("changes", CLIENT_NAME_CHANGED);
            }
        }
    }

    /**
     * Обработка добавления животного
     * @param req Запрос
     */
    private void processAddPet(HttpServletRequest req) {
        if (req.getParameter("addPet") != null) {
            String petName = req.getParameter("petName");

            if (!"".equals(petName)) {
                CLINIC.addPetToCurrentClient(req.getParameter("petType"), petName);
            }
        }
    }

    /**
     * Обработка удаления клиента
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processRemoveClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("remove") != null) {
            CLINIC.removeCurrentClient();
            forwardTo(req, resp, CLINIC_VIEW);
        }
    }

    /**
     * Перенаправление на нужную страницу в зависимости от состояния текущего клиента.
     * Если клиента удалили, то текущего не будет, и т.п.
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void forwardDependsOfClientState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CLINIC.getCurrentClient() == null) {
            forwardTo(req, resp, CLINIC_VIEW);
        } else {
            req.setAttribute("client", CLINIC.getCurrentClient());
            forwardTo(req, resp, EDIT_CLIENT);
        }
    }

}
