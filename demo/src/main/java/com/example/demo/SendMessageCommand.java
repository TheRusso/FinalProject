package com.example.demo;

import db.ContactsDAO;
import db.entities.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class SendMessageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Message message = getMessageFromRequest(request);

        ContactsDAO contactsDAO = new ContactsDAO();
        boolean isInserted = contactsDAO.insertMessage(message);

        Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("errors", locale);

        String forward = Path.PAGE__ERROR_PAGE.value;
        request.setAttribute("errorMessage", bundle.getString("dataValidationError"));

        if(!isInserted)
            return forward;


        forward = Path.MAIN_PAGE.value;

        return forward;
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
