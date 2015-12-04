package com.bikmop.servlets;

import com.bikmop.petclinic.pet.Pet;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClinicServlet extends HttpServlet {

    final List<Pet> pets = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>PET CLINIC</title>" +
                        "</head>" +
                        "<body>" +
                        "     <p>PETS:</p>" +
                        getTableOfPets(this.pets) +
                        "     <br></br>" +
                        "     <hr></hr>" +
                        getAddPetForm(req) +
                        "     <br></br>" +
                        "     <br></br>" +
                        "     <hr></hr>" +
                        getSearchForm(req) +
                        "     <br></br>" +
                        "     <hr></hr>" +
                        getDelPetForm(req) +
                        "     <hr></hr>" +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        buttonAddHandler(req);
        buttonDelHandler(req);
        doGet(req, resp);
    }

    private String getSearchForm(HttpServletRequest req) {
        return "     <form action='" + req.getContextPath() + "/' method='post'>" +
                "         <p>Pets search:</p>" +
                "         Pet's type:" +
                "         <select name='typeSearch'>" +
                "           <option value=''>All" +
                "           <option value='1'>Cat" +
                "           <option value='2'>Dog" +
                "           <option value='3'>Fish" +
                "           <option value='4'>Bird" +
                "           <option value='5'>Reptile" +
                "           <option value='6'>Rodent" +
                "           <option value='7'>Another" +
                "         </select>" +
                "         Part of pet's name: " +
                "         <input type='text' name='nameSearch'>" +
                "         <input type='submit' name='search' value='Search'>" +
                          getSearchResult(req) +
                "     <form>";
    }

    private String getSearchResult(HttpServletRequest req) {
        String result = "<br><br>";

        if (req.getParameter("search") != null) {
            List<Pet> pets = searchPets(req.getParameter("typeSearch"), req.getParameter("nameSearch"));
            if (pets.isEmpty())
                result = "<br><br>Nothing found!";
            else
                result = getTableOfPets(pets);
        }

        return result;
    }

    private String getAddPetForm(HttpServletRequest req) {
        return "     <form action='" + req.getContextPath() + "/' method='post'>" +    //форма ввода
                "         <p>Add pet:</p>" +
                "         Pet's type:" +
                "         <select name='typeAdd'>" +
                "           <option value='7'>Another" +
                "           <option value='1'>Cat" +
                "           <option value='2'>Dog" +
                "           <option value='3'>Fish" +
                "           <option value='4'>Bird" +
                "           <option value='5'>Reptile" +
                "           <option value='6'>Rodent" +
                "         </select>" +
                "         Pet's name: " +
                "         <input type='text' name='nameAdd'>" +
                "         <input type='submit' name='add' value='Add'>" +
                "     <form>";
    }

    private String getDelPetForm(HttpServletRequest req) {
        return "     <form action='" + req.getContextPath() + "/' method='post'>" +
                "         <p>Delete pet:</p>" +
                "         Pet's name: " +
                "         <input type='text' name='nameRemove'>" +
                "         <input type='submit' name='del' value='Del'>" +
                "     <form>";
    }

    private void buttonDelHandler(HttpServletRequest req) {
        if (isNotNull(req.getParameter("del"))) {
            String petNameToRemove = req.getParameter("nameRemove");

            if (isNotEmptyString(petNameToRemove))
                removePetByName(petNameToRemove);
        }
    }

    private boolean isNotNull(Object o) {
        return o != null;
    }

    private boolean isNotEmptyString(String s) {
        return !"".equals(s);
    }

    private void buttonAddHandler(HttpServletRequest req) {
        if (isNotNull(req.getParameter("add"))) {
            String petName = req.getParameter("nameAdd");

            if (isNotEmptyString(petName)) {
                PetType petType = PetType.getPetTypeByString(req.getParameter("typeAdd"));

                this.pets.add(PetFactory.createPet(petType, petName));
            }
        }
    }

    private List<Pet> searchPets(String typeSearch, String nameSearch) {
        List<Pet> result = getByType(typeSearch);
        checkNames(result, nameSearch);

        return result;
    }

    private void checkNames(List<Pet> result, String nameSearch) {
        if (isNotNull(nameSearch) && isNotEmptyString(nameSearch))
            for (Pet pet : result)
                if (!hasInName(pet, nameSearch))
                    result.remove(pet);
    }

    private boolean hasInName(Pet pet, String nameSearch) {
        return pet.getName().toLowerCase().contains(nameSearch.toLowerCase());
    }

    private List<Pet> getByType(String typeSearch) {
        List<Pet> result = new CopyOnWriteArrayList<>();

        if (isNotNull(typeSearch) && isNotEmptyString(typeSearch)) {
            result = getFromCorrectSearchType(typeSearch);
        } else if (!isNotEmptyString(typeSearch)) {
            result.addAll(this.pets);
        }
        return result;
    }

    private List<Pet> getFromCorrectSearchType(String typeSearch) {
        List<Pet> result = new CopyOnWriteArrayList<>();
        for (Pet pet : this.pets)
            if (pet.getClass() == PetFactory.createPet(PetType.getPetTypeByString(typeSearch), "Name").getClass())
                result.add(pet);

        return result;
    }

    private String getTableOfPets(List<Pet> pets) {

        StringBuilder sb = new StringBuilder();
        if (!pets.isEmpty()) {
            sb.append("<br></br>");
            sb.append("<table style='border : 1px solid black'>");
            sb.append("<tr><td style='border : 2px solid black'>").append("Type").append("</td>")
                    .append("<td style='border : 2px solid black'>").append("Name").append("</td></tr>");
            for (Pet pet : pets) {
                sb.append("<tr><td style='border : 1px solid black'>").append(pet.getStringPetType()).append("</td>")
                        .append("<td style='border : 1px solid black'>").append(pet.getName()).append("</td></tr>");
            }
            sb.append("</table>");
        }
        return sb.toString();
    }

    private void removePetByName(String petsFullName) {
        for (Pet pet : this.pets)
            if (hasPetFullName(pet, petsFullName))
                this.pets.remove(pet);
    }

    private boolean hasPetFullName(Pet pet, String checkedName) {
        return pet.getName().equals(checkedName);
    }

}
