package com.bikmop.servlets;



import com.bikmop.petclinic.client.Client;
import com.bikmop.store.PetClinic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет страницы добавления клиента
 */
public class AddClientServlet extends HttpServlet {
    /** Строковые константы */
    private static final String INPUT_ID = "Введите Id!";
    private static final String CLIENT_PRESENT = "Клиент с таким Id уже существует!";
    private static final String EDIT_CLIENT_PATH = "/client/edit";
    private static final String ADD_CLIENT = "/view/client/AddClient.jsp";

    /** Экземпляр клиники */
    private final PetClinic CLINIC = PetClinic.getInstance();


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
        processAddClient(req, resp);
        forwardTo(req, resp, ADD_CLIENT);
    }


    /**
     * Обработка кнопки добавления клиента в зависимости от корректности Id
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processAddClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("add") != null) {
            if (isCorrectId(req.getParameter("id"))) {
                addClient(req, resp);
            } else {
                askCorrectId(req, resp);
            }
        }
    }

    /**
     * Проверка id на корректность - не пустая строка и отсутствие у других клиентов клиники
     * @param id Идентификатор
     * @return Корректность
     */
    private boolean isCorrectId(String id) {
        return !"".equals(id) && CLINIC.isUniqueClientId(id);
    }

    /**
     * Добавление клиента в клинику и перенаправление на страницу редактирования для возможности добавить животных
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        CLINIC.addClient(new Client(name, id));
        CLINIC.selectFirstMatchingClient(Client.SearchType.ID_FULL, id);
        forwardTo(req, resp, EDIT_CLIENT_PATH);
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
     * Сообщаем об ошибке в Id.
     * Остаемся на той же странице с теми же значениями полей ввода.
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void askCorrectId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("idValue", req.getParameter("id"));
        req.setAttribute("nameValue", req.getParameter("name"));
        req.setAttribute("errorValue", getErrorMessage(req));

        forwardTo(req, resp, ADD_CLIENT);
    }

    /**
     * Получить нужное сообщение об ошибке в зависимости от введенного Id
     * @param req Запрос
     * @return Текст ошибки
     */
    private String getErrorMessage(HttpServletRequest req) {
        String errorMessage;
        if ("".equals(req.getParameter("id")))
            errorMessage = INPUT_ID;
        else
            errorMessage = CLIENT_PRESENT;

        return errorMessage;
    }

}
