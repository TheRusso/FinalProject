package com.example.demo.controllers.open_view;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.BeanDAO;
import com.example.demo.db.bean.UsersBean;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public class OpenAdminUsersCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        List<UsersBean> beanList = new UserDAO().findUsersBean();

        beanList.sort(Comparator.comparingLong(UsersBean::getId).reversed());

        request.setAttribute("users_bean", beanList.toArray());

        return new ServletResponse(Path.ADMIN_USERS.getValue());
    }
}
