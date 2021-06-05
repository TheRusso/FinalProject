package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.bean.UsersBean;
import com.example.demo.services.serviceImpl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public class OpenAdminUsersCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        List<UsersBean> beanList = new UserService().findUsersBean();

        beanList.sort(Comparator.comparingLong(UsersBean::getId).reversed());

        request.setAttribute("users_bean", beanList.toArray());

        return new ServletResponse(Path.ADMIN_USERS.getValue());
    }
}
