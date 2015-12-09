package com.bikmop.servlets;

import com.bikmop.store.PetClinic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClinicViewServlet extends HttpServlet {

    private final PetClinic CLINIC = PetClinic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clinic", "qwerty");
        req.setAttribute("number", 123456.1515151511);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/ClinicView.jsp");
        dispatcher.forward(req, resp);
    }



}
