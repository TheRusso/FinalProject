package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.User;
import com.example.demo.services.serviceImpl.UserService;
import com.example.demo.utils.Configuration;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserCommand extends Command {

    private final Logger logger = Logger.getLogger(RegisterUserCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Started registering user");

        if(request.getMethod().equals("POST")){

            logger.info("Method POST");


            if(!request.getParameter("password").equals(request.getParameter("conf_password")) ||
                    request.getParameter("first_name").isEmpty() || request.getParameter("second_name").isEmpty() ||
                    request.getParameter("email").isEmpty()){

                logger.warn("Invalid data");
                ErrorUtil.printErrorMessage(ErrorPropNamesHandler.DATA_VALIDATION_ERROR, request);

                return new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());
            }

            logger.info("Valid data");

            User user = createUserFromRequest(request);

            boolean isRegistered = new UserService().insert(user);

            if(isRegistered){
                request.getSession().setAttribute("user", user);


                logger.info("The user have been registered");

                if(request.getParameter("to") != null && request.getParameter("to").equals("order")){
                    logger.info("Redirect to: " + request.getParameter("to"));

                    return new ServletResponse("view/order");
                }

                return new ServletResponse.Builder()
                        .withPath(Path.MAIN_PAGE.getValue())
                        .withRedirect(RedirectType.REDIRECT)
                        .build();
            }else{
                logger.warn("The user have not been registered");

                ErrorUtil.printErrorMessage(ErrorPropNamesHandler.SAVE_NEW_USER_ERROR, request);

                return new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());
            }

        }else{
            logger.warn("GET method, so we threw an exception");
            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE, request);

            return new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());
        }
    }

    private User createUserFromRequest(HttpServletRequest request){
        User user = new User();
        user.setFirst_name(request.getParameter("first_name"));
        user.setSecond_name(request.getParameter("second_name"));
        user.setEmail(request.getParameter("email"));
        user.setPass(request.getParameter("password"));
        return user;
    }
}
