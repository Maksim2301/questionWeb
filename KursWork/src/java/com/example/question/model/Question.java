package com.example.question.model;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Integer id;
    private String question;
    private boolean answered;
    private String title;
    private String keywords;
    private Integer userId; 
    private List<String> answers = new ArrayList<>();

    public Question(Integer id, String question, boolean answered, String title, String keywords, Integer userId) {
        this.id = id;
        this.question = question;
        this.answered = answered;
        this.title = title;
        this.keywords = keywords;
        this.userId = userId;
    }

    public Question(String question, boolean answered, String title, String keywords, Integer userId) {
        this.question = question;
        this.answered = answered;
        this.title = title;
        this.keywords = keywords;
        this.userId = userId; 
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
     
    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}