package com.southwind.controller;

import com.southwind.dto.DormitoryAdminDto;
import com.southwind.dto.SystemAdminDto;
import com.southwind.service.DormitoryAdminService;
import com.southwind.service.SystemAdminService;
import com.southwind.service.impl.DormitoryAdminServiceImpl;
import com.southwind.service.impl.SystemAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private SystemAdminService systemAdminService = new SystemAdminServiceImpl();
    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "login":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String type = req.getParameter("type");
                switch (type){
                    case "systemAdmin":
                        SystemAdminDto systemAdminDto = this.systemAdminService.login(username, password);
                        switch (systemAdminDto.getCode()){
                            case -1:
                                req.setAttribute("usernameError", "用户名不存在");
                                req.getRequestDispatcher("login.jsp").forward(req, resp);
                                break;
                            case -2:
                                req.setAttribute("passwordError", "密码错误");
                                req.getRequestDispatcher("login.jsp").forward(req, resp);
                                break;
                            case 0:
                                //跳转到登录成功界面
                                HttpSession session = req.getSession();
                                session.setAttribute("systemAdmin", systemAdminDto.getSystemAdmin());
                                resp.sendRedirect("/systemadmin.jsp");
                                break;
                        }
                        break;
                    case "dormitoryAdmin":
                        DormitoryAdminDto dormitoryAdminDto = this.dormitoryAdminService.login(username, password);
                        switch (dormitoryAdminDto.getCode()){
                            case -1:
                                req.setAttribute("usernameError", "用户名不存在");
                                req.getRequestDispatcher("login.jsp").forward(req, resp);
                                break;
                            case -2:
                                req.setAttribute("passwordError", "密码错误");
                                req.getRequestDispatcher("login.jsp").forward(req, resp);
                                break;
                            case 0:
                                //跳转到登录成功界面
                                HttpSession session = req.getSession();
                                session.setAttribute("dormitoryAdmin", dormitoryAdminDto.getDormitoryAdmin());
                                resp.sendRedirect("/dormitoryadmin.jsp");
                                break;
                        }
                        break;
                }
                break;
            case "logout":
                req.getSession().invalidate();
                resp.sendRedirect("/login.jsp");
                break;
        }

    }
}
