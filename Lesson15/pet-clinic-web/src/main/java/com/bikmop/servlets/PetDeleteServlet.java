package com.bikmop.servlets;

import com.bikmop.store.PetClinic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обработки удаления животного у клиента
 */
public class PetDeleteServlet extends HttpServlet {
    /** Строковая константа */
    private static final String EDIT_CLIENT_PATH = "/client/edit";

    /** Экземпляр клиники */
    private final PetClinic CLINIC = PetClinic.getInstance();


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
        removePet(req);
        refreshPage(req, resp);
    }

    /**
     * Удаление животного у клиента клиники
     * @param req Запрос
     */
    private void removePet(HttpServletRequest req) {
        CLINIC.getCurrentClient().removePetByName(req.getParameter("name"));
    }

    /**
     * Возвращаемся на ту же страницу редактирования и обновляем ее.
     * Удаление животного делается ссылкой со страницы редактирования клиента. На нее и возвращаемся.
     * @param req Запрос
     * @param resp Ответ
     * @throws IOException
     */
    private void refreshPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("client", CLINIC.getCurrentClient());
        String id = "?id=" + CLINIC.getCurrentClient().getId();
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), EDIT_CLIENT_PATH, id ));
    }
}
