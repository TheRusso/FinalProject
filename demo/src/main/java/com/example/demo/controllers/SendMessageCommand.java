package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.db.dao.ContactsDAO;
import com.example.demo.db.entities.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class SendMessageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.getValue());

        if(request.getMethod().equals("POST")){
            Message message = getMessageFromRequest(request);

            ContactsDAO contactsDAO = new ContactsDAO();
            boolean isInserted = contactsDAO.insertMessage(message);

            Locale locale = Locale.getDefault();
            ResourceBundle bundle = ResourceBundle.getBundle("errors", locale);


            request.setAttribute("errorMessage", bundle.getString("dataValidationError"));

            if(!isInserted){
                servletResponse.setPath(Path.PAGE__ERROR_PAGE.getValue());
                request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("dataValidationError"));
                return servletResponse;
            }


            servletResponse.setPath(Path.MAIN_PAGE.getValue());
            servletResponse.setRedirectType(RedirectType.REDIRECT);
        }

        return servletResponse;
    }

    private Message getMessageFromRequest(HttpServletRequest request){
        Message message = new Message();

        message.setName(request.getParameter("name"));
        message.setEmail(request.getParameter("email"));
        message.setPhone_number(request.getParameter("number"));
        message.setMessage(request.getParameter("message"));

        return message;
    }
}
