package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.dao.ContactsDAO;
import com.example.demo.db.entities.Message;
import com.example.demo.utils.Configuration;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class SendMessageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE, request);
        ServletResponse servletResponse = new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());

        if(request.getMethod().equals("POST")){
            Message message = getMessageFromRequest(request);

            ContactsDAO contactsDAO = new ContactsDAO();
            boolean isInserted = contactsDAO.insertMessage(message);

            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.DATA_VALIDATION_ERROR, request);

            if(!isInserted){
                servletResponse.setPath(Path.PAGE_ERROR_PAGE.getValue());
                ErrorUtil.printErrorMessage(ErrorPropNamesHandler.DATA_VALIDATION_ERROR, request);
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
