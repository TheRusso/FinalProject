package com.example.demo.services.service_impl;

import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.DBManager;
import com.example.demo.db.Role;
import com.example.demo.db.bean.UsersBean;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import com.example.demo.services.ServiceEntity;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService implements ServiceEntity<User> {
    private final Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserDAO userDAO = new UserDAO();

    @Override
    public List<User> findAll() {
        List<User> userList = null;

        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            userList = userDAO.findAll(connection);
        } catch (SQLException exception) {
            logger.error(exception);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return userList;
    }

    @Override
    public User findById(Long id) {
        Connection connection = null;
        User user = null;

        try {
            connection = DBManager.getInstance().getConnection();
            user = userDAO.findUser(id, connection);
        } catch (SQLException exception) {
            logger.error(exception);
            DBManager.getInstance().rollbackAndClose(connection);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return user;
    }

    @Override
    public boolean insert(User attr) {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            userDAO.insertUser(attr, connection);
        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error(exception);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return true;
    }

    @Override
    public boolean update(User attr) {
        Connection connection = null;
        try {
            connection= DBManager.getInstance().getConnection();
            userDAO.updateUser(attr, connection);
        } catch (SQLException | IOException exception) {
            logger.error(exception);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return true;
    }

    public User findByEmail(String email){
        User user = null;

        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            user = userDAO.findUser(email, connection);
        } catch (SQLException exception) {
            logger.error(exception);
            DBManager.getInstance().rollbackAndClose(connection);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return user;
    }

    public List<UsersBean> findUsersBean(){
        List<UsersBean> list = null;

        try {
            list = userDAO.findUsersBean(DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }

        return list;
    }

    public ServletResponse logout(HttpServletRequest request){
        ServletResponse servletResponse = new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());

        HttpSession session = request.getSession();

        if(session.getAttribute("user") != null){
            session.removeAttribute("user");

            servletResponse = new ServletResponse.Builder()
                    .withPath(Path.MAIN_PAGE.getValue())
                    .withRedirect(RedirectType.REDIRECT)
                    .build();

            return servletResponse;
        }

        ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE, request);
        return servletResponse;
    }

    public ServletResponse loginUser(HttpServletRequest request){
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        logger.info("Request parameter: login -->" + email);

        String password = request.getParameter("password");

        ServletResponse servletResponse = new ServletResponse.Builder()
                .withPath(Path.PAGE_ERROR_PAGE.getValue())
                .withRedirect(RedirectType.FORWARD)
                .build();

        User user = findByEmail(email);

        logger.info("Found in DB: user --> " + user);

        if(user == null || !password.equals(user.getPass()) || user.getBanned() == 1) {
            logger.info("No such user or denied access");
            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_USER, request);

            return servletResponse;
        }

        Role userRole = Role.getRole(user);
        logger.info("userRole --> " + userRole);

        setRedirectForRole(userRole, servletResponse, request);

        setUserInSession(user, userRole, session);

        setRedirectIfExist(servletResponse, request);

        logger.info("Command finished");

        return servletResponse;
    }

    private void setUserInSession(User user,
                                  Role userRole,
                                  HttpSession session){
        session.setAttribute("user", user);
        logger.info("Set user in the session");

        session.setAttribute("userRole", userRole);
        logger.info("Set the session attribute: userRole --> " + userRole);
    }

    private void setRedirectForRole(Role userRole, ServletResponse servletResponse, HttpServletRequest request){
        if(userRole == Role.ADMIN)
            servletResponse.setPath(request.getContextPath() + Path.ADMIN_PANEL_URL);
        else if(userRole == Role.CLIENT)
            servletResponse.setPath(request.getContextPath() + Path.USER_PAGE_URL);

        servletResponse.setRedirectType(RedirectType.REDIRECT);
    }

    private void setRedirectIfExist(ServletResponse servletResponse, HttpServletRequest request){
        logger.info("Redirect to: " + request.getParameter("to"));

        if(request.getParameter("to") != null && request.getParameter("to").equals("order"))
            servletResponse.setPath(Path.ORDER_FORM_URL.getValue());

    }

    public ServletResponse register(HttpServletRequest request){
        logger.info("Started registering user");

        User user = createUserFromRequest(request);

        ServletResponse servletResponse = new ServletResponse.Builder()
                .withPath(Path.MAIN_PAGE.getValue())
                .withRedirect(RedirectType.REDIRECT)
                .build();

        boolean isRegistered = insert(user);

        if(isRegistered){

            setUserInSession(findByEmail(user.getEmail()), Role.CLIENT, request.getSession());

            logger.info("The user have been registered");

            setRedirectIfExist(servletResponse, request);

            return servletResponse;
        }else{
            logger.warn("The user have not been registered");

            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.SAVE_NEW_USER_ERROR, request);
            return new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());
        }
    }

    public User createUserFromRequest(HttpServletRequest request){
        User user = new User();
        user.setFirst_name(request.getParameter("first_name"));
        user.setSecond_name(request.getParameter("second_name"));
        user.setEmail(request.getParameter("email"));
        user.setPass(request.getParameter("password"));
        return user;
    }
}
