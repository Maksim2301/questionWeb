package com.example.question.web;
import com.example.question.dao.QuestionDao;
import com.example.question.dao.UserDao;
import com.example.question.dao.InMemoryQuestionDao;
import com.example.question.dao.InMemoryUserDao;
import com.example.question.util.Database;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class QuestionServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        QuestionDao questionDao = new InMemoryQuestionDao();
        UserDao userDao = new InMemoryUserDao();
        Database.createDatabase(questionDao, userDao);
        sce.getServletContext().setAttribute("questionDao", questionDao);
        sce.getServletContext().setAttribute("userDao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
