package org.example.web;

import org.example.config.ObjectMapperConfiguration;
import org.example.domain.Contact;
import org.example.service.ContactService;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {

    public static ContactService contactService= new ContactService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     addCorsHeaders(resp);

        CreateContactRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), CreateContactRequest.class);

        try {
            contactService.createContact(request);
        } catch (SQLException e) {
            resp.sendError(500,e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

addCorsHeaders(resp);

        try {
            List<Contact> contacts=contactService.getContact();

            ObjectMapperConfiguration.getObjectMapper().writeValue(resp.getWriter(),contacts);
        } catch (SQLException e) {
           resp.sendError(500,e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       addCorsHeaders(resp);

        String idAsString = req.getParameter("id");

        try {
            contactService.deleteContact(Long.parseLong(idAsString));
        } catch (SQLException e) {
            resp.sendError(500,e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);

        String idAsString = req.getParameter("id");

        UpdateContactRequest request = ObjectMapperConfiguration.getObjectMapper()
                .readValue(req.getReader(), UpdateContactRequest.class);

        try {

            contactService.updateContact(Long.parseLong(idAsString),request);
        } catch (SQLException e) {
            resp.sendError(500,e.getMessage());
        }
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);


    }

    private void addCorsHeaders(HttpServletResponse response){

        //What origin do we allow
        response.addHeader("Access-Control-Allow-Origin","*");
        //What kind of requests do we allow
        response.addHeader("Access-Control-Allow-Methods","POST, GET, PUT, DELETE, OPTIONS");
       //And what kind of headers do we allow
        response.addHeader("Access-Control-Allow-Headers","content-type");


    }
}
