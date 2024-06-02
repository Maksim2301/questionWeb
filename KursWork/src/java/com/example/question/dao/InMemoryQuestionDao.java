package com.example.question.dao;

import com.example.question.model.Question;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class InMemoryQuestionDao implements QuestionDao {

    private final TreeMap<Integer, Question> questions = new TreeMap<>();

    @Override
    public void create(Question question) {
        int id = questions.isEmpty() ? 1 : questions.lastKey() + 1;
        question.setId(id);
        questions.put(id, question);
    }

    @Override
    public Question findById(Integer id) {
        return questions.get(id);
    }

    @Override
    public Collection<Question> findAll() {
        return questions.values();
    }

    @Override
    public void update(Question question) {
        questions.put(question.getId(), question);
    }

    @Override
    public void deleteById(Integer id) {
        questions.remove(id);
    }

    @Override
    public Collection<Question> findByKeywords(String[] keywords) {
        List<Question> matchingQuestions = new ArrayList<>();
        for (Question question : findAll()) {
            for (String keyword : keywords) {
                if (question.getKeywords().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingQuestions.add(question);
                    break;
                }
            }
        }
        return matchingQuestions;
    }

    @Override
    public Collection<Question> findByUserId(Integer userId) {
        List<Question> result = new ArrayList<>();
        for (Question question : questions.values()) {
            if (question.getUserId().equals(userId)) {
                result.add(question);
            }
        }
        return result;
    }

    @Override
    public void addAnswer(Integer questionId, String answer) {
        Question question = questions.get(questionId);
        if (question != null) {
            String updatedQuestion = question.getQuestion() + "\n\nAnswer: " + answer;
            question.setQuestion(updatedQuestion);
            update(question);
        }
    }
}
