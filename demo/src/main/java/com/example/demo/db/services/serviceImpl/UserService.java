package com.example.demo.db.services.serviceImpl;

import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.Role;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import com.example.demo.db.services.Service;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService implements Service<User>{
    private Logger logger = Logger.getLogger(UserService.class.getName());

    private UserDAO userDAO = new UserDAO();

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findUser(id);
    }

    @Override
    public boolean insert(User attr) {
        return userDAO.insertUser(attr);
    }

    @Override
    public boolean update(User attr) {
        return userDAO.updateUser(attr);
    }

    public User findByEmail(String email){
        return userDAO.findUser(email);
    }

    public ServletResponse logout(HttpServletRequest request){
        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.getValue());

        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            session.setAttribute("user", null);

            servletResponse.setPath(Path.MAIN_PAGE.getValue());
            servletResponse.setRedirectType(RedirectType.REDIRECT);
        }

        return servletResponse;
    }

    public ServletResponse loginUser(HttpServletRequest request){
        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.getValue());

        if(request.getMethod().equals("POST")){
            HttpSession session = request.getSession();

            //obtain login and password from the request
            String email = request.getParameter("email");
            logger.info("Request parameter: login -->" + email);

            String password = request.getParameter("password");


            //error handler
            String errorMessage = null;
            servletResponse.setPath(Path.PAGE__ERROR_PAGE.getValue());
            servletResponse.setRedirectType(RedirectType.FORWARD);

            if(email == null || password == null || email.isEmpty() || password.isEmpty()){
                errorMessage = "Login/Password cannot be empty";
                request.setAttribute("errorMessage", errorMessage);
                logger.warn("errorMessage --> " + errorMessage);
                return servletResponse;
            }

            User user = findByEmail(email);

            logger.info("Found in DB: user --> " + user);

            if(user == null || !password.equals(user.getPass()) || user.getBanned() == 1) {
                errorMessage = "Cannot find user with such login/password";
                request.setAttribute("errorMessage", errorMessage);
                logger.info("errorMessage --> " + errorMessage);
                return servletResponse;
            }else {
                Role userRole = Role.getRole(user);
                logger.info("userRole --> " + userRole);

                if(userRole == Role.ADMIN){
                    servletResponse.setPath(request.getContextPath() + Path.ADMIN_PANEL_URL);
                }
                else if(userRole == Role.CLIENT) {
                    servletResponse.setPath(request.getContextPath() + Path.USER_PAGE_URL);
                }

                session.setAttribute("user", user);
                logger.info("Set the session attribute: email --> " + user.getEmail());
                logger.info("Set the session attribute: pass --> " + user.getPass());

                session.setAttribute("userRole", userRole);
                logger.info("Set the session attribute: userRole --> " + userRole);

                logger.info("User " + user + " logged as " + userRole.toString().toLowerCase());

                logger.info("Redirect to: " + request.getParameter("to"));

                servletResponse.setRedirectType(RedirectType.REDIRECT);
                if(request.getParameter("to") != null && request.getParameter("to").equals("order")){
                    servletResponse.setPath(Path.ORDER_FORM_URL.getValue());

                    return servletResponse;
                }
            }
            logger.info("Command finished");

            servletResponse.setRedirectType(RedirectType.REDIRECT);
        }

        return servletResponse;
    }
}
