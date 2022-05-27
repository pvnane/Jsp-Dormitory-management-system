package com.southwind.controller;

import com.southwind.entity.Student;
import com.southwind.service.DormitoryService;
import com.southwind.service.StudentService;
import com.southwind.service.impl.DormitoryServiceImpl;
import com.southwind.service.impl.StudentServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
                req.setAttribute("list", this.studentService.list());
                req.setAttribute("dormitoryList", this.dormitoryService.availableList());
                req.getRequestDispatcher("studentmanager.jsp").forward(req, resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list", this.studentService.search(key, value));
                req.setAttribute("dormitoryList", this.dormitoryService.availableList());
                req.getRequestDispatcher("studentmanager.jsp").forward(req, resp);
                break;
            case "save":
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String number = req.getParameter("number");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                this.studentService.save(new Student(number, name, gender, dormitoryId));
                resp.sendRedirect("/student?method=list");
                break;
            case "update":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                dormitoryIdStr = req.getParameter("dormitoryId");
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                number = req.getParameter("number"); 
                name = req.getParameter("name");
                gender = req.getParameter("gender");
                String oldDormitoryIdStr = req.getParameter("oldDormitoryId");
                Integer oldDormitoryId = Integer.parseInt(oldDormitoryIdStr);
                this.studentService.update(new Student(id, number, name, gender, dormitoryId),oldDormitoryId);
                resp.sendRedirect("/student?method=list");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                dormitoryIdStr = req.getParameter("dormitoryId");
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                this.studentService.delete(id, dormitoryId);
                resp.sendRedirect("/student?method=list");
                break;
            case "findByDormitoryId":
                dormitoryIdStr = req.getParameter("dormitoryId");
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                List<Student> studentList = this.studentService.findByDormitoryId(dormitoryId);
                JSONArray jsonArray = JSONArray.fromObject(studentList);
                resp.setContentType("text/json;charset=UTF-8");
                resp.getWriter().write(jsonArray.toString());
                break;
        }
    }
}
