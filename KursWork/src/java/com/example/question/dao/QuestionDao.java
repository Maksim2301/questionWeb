package com.example.question.dao;

import com.example.question.model.Question;
import java.util.Collection;

public interface QuestionDao {
    void create(Question question);
    Question findById(Integer id);
    Collection<Question> findAll();
    Collection<Question> findByKeywords(String[] keywords);
    Collection<Question> findByUserId(Integer userId);
    void update(Question question);
    void deleteById(Integer id);
    void addAnswer(Integer questionId, String answer); 
}
