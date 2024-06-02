package com.example.question.controllers;

import com.example.question.dao.QuestionDao;
import com.example.question.dao.UserDao;
import com.example.question.model.Question;
import com.example.question.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="QuestionController", urlPatterns={"/questions", "/index.html"})
public class QuestionController extends HttpServlet {

    private QuestionDao questionDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        questionDao = (QuestionDao) getServletContext().getAttribute("questionDao");
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "showDashboard";
        }
        switch (command) {
            case "edit" -> showEditForm(request, response);
            case "search" -> searchByKeywords(request, response);
            case "createForm" -> showCreateForm(request, response);
            case "showDashboard" -> showDashboard(request, response);
            case "showAddAnswerForm" -> showAddAnswerForm(request, response); 
            default -> showAllQuestions(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            response.sendError(400, "parameter command not found");
            return;
        }
        switch (command) {
            case "delete" -> delete(request, response);
            case "create" -> create(request, response);
            case "save" -> save(request, response);
            case "addAnswer" -> addAnswer(request, response); 
            default -> response.sendError(400, "wrong command");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            questionDao.deleteById(id);
            response.sendRedirect(request.getContextPath() + "/questions");
        } catch (IOException | NumberFormatException e) {
            response.sendError(400);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String title = request.getParameter("title");
            String question = request.getParameter("question");
            String keywords = request.getParameter("keywords");
            boolean answered = request.getParameter("answered") != null;

            Question questions = new Question(question, answered, title, keywords, user.getId());
            questionDao.create(questions);
            response.sendRedirect(request.getContextPath() + "/questions");
        } catch (IOException e) {
            response.sendError(400);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            String title = request.getParameter("title");
            String question = request.getParameter("question");
            boolean answered = request.getParameter("answered") != null;
            String keywords = request.getParameter("keywords");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            Question questions = new Question(id, question, answered, title, keywords, user.getId());
            questionDao.update(questions);
            response.sendRedirect(request.getContextPath() + "/questions");
        } catch (IOException | NumberFormatException e) {
            response.sendError(400);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        Collection<Question> questions = questionDao.findByUserId(user.getId());
        Map<Integer, String> userNames = new HashMap<>();
        for (Question question : questions) {
            User questionUser = userDao.findById(question.getUserId());
            userNames.put(question.getUserId(), questionUser.getName());
        }
        request.setAttribute("questions", questions);
        request.setAttribute("userNames", userNames);
        request.getRequestDispatcher("WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Question questions = questionDao.findById(id);
            request.setAttribute("questions", questions);
            request.getRequestDispatcher("WEB-INF/jsp/edit.jsp").forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            response.sendError(400);
        }
    }

    private void searchByKeywords(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword").trim();
            if (keyword.isEmpty()) {
                request.setAttribute("errorMessage", "Please enter a keyword");
                showAllQuestions(request, response);
                return;
            }
            String[] keywords = keyword.split("\\s+");
            Collection<Question> questions = questionDao.findByKeywords(keywords);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            Map<Integer, String> userNames = new HashMap<>();
            for (Question question : questions) {
                User questionUser = userDao.findById(question.getUserId());
                userNames.put(question.getUserId(), questionUser.getName());
            }
            request.setAttribute("questions", questions);
            request.setAttribute("userNames", userNames);
            request.getRequestDispatcher("WEB-INF/jsp/all-questions.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            response.sendError(400);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/new-question.jsp").forward(request, response);
    }

    private void showAllQuestions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Collection<Question> questions = questionDao.findAll();
        Map<Integer, String> userNames = new HashMap<>();
        for (Question question : questions) {
            User questionUser = userDao.findById(question.getUserId());
            userNames.put(question.getUserId(), questionUser.getName());
        }
        request.setAttribute("questions", questions);
        request.setAttribute("userNames", userNames);
        request.getRequestDispatcher("WEB-INF/jsp/all-questions.jsp").forward(request, response);
    }

    private void showAddAnswerForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Question question = questionDao.findById(id);
            request.setAttribute("question", question);
            request.getRequestDispatcher("WEB-INF/jsp/add-answer.jsp").forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            response.sendError(400);
        }
    }

    private void addAnswer(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        Integer questionId = Integer.valueOf(request.getParameter("questionId"));
        String answer = request.getParameter("answer").trim();
        
        if (answer.isEmpty()) {
            Collection<Question> questions = questionDao.findAll();
            Map<Integer, String> userNames = new HashMap<>();
            for (Question question : questions) {
                User questionUser = userDao.findById(question.getUserId());
                userNames.put(question.getUserId(), questionUser.getName());
            }
            request.setAttribute("questions", questions);
            request.setAttribute("userNames", userNames);
            request.setAttribute("errorMessage", "Answer cannot be empty");
            request.getRequestDispatcher("WEB-INF/jsp/all-questions.jsp").forward(request, response);
            return;
        }

        questionDao.addAnswer(questionId, answer);
        response.sendRedirect(request.getContextPath() + "/questions?command=showAllQuestions");
    } catch (IOException | NumberFormatException e) {
        response.sendError(400);
    }
}

}