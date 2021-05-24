package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * No command.
 *
 * @author D.Kolesnikov
 *
 */
public class NoCommand extends Command {

    private static final long serialVersionUID = -2785976616686657267L;

    private static final Logger log = Logger.getLogger(NoCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        log.severe("Command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        log.severe("Set the request attribute: errorMessage --> " + errorMessage);

        log.severe("Command finished");
        return Path.PAGE__ERROR_PAGE.getValue();
    }

}