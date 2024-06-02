package com.example.question.util;

import com.example.question.dao.QuestionDao;
import com.example.question.dao.UserDao;
import com.example.question.model.Question;
import com.example.question.model.User;

public class Database {

    public static void createDatabase(QuestionDao questionDao, UserDao userDao) {
        userDao.create(new User(null, "Alice", "alice@example.com", "password"));
        userDao.create(new User(null, "Bob", "bob@example.com", "password1"));
        userDao.create(new User(null, "Charlie", "charlie@example.com", "password123"));
        userDao.create(new User(null, "Diana", "diana@example.com", "securepassword"));
        userDao.create(new User(null, "Eve", "eve@example.com", "mypassword"));
        userDao.create(new User(null, "Frank", "frank@example.com", "frankspassword"));
        userDao.create(new User(null, "Grace", "grace@example.com", "grace2021"));
        userDao.create(new User(null, "Hank", "hank@example.com", "hanktheman"));
        userDao.create(new User(null, "Ivy", "ivy@example.com", "ivyivyivy"));
        userDao.create(new User(null, "Jack", "jack@example.com", "jack1234"));

        User alice = userDao.findByEmail("alice@example.com");
        User bob = userDao.findByEmail("bob@example.com");
        User charlie = userDao.findByEmail("charlie@example.com");
        User diana = userDao.findByEmail("diana@example.com");
        User eve = userDao.findByEmail("eve@example.com");
        User frank = userDao.findByEmail("frank@example.com");
        User grace = userDao.findByEmail("grace@example.com");
        User hank = userDao.findByEmail("hank@example.com");
        User ivy = userDao.findByEmail("ivy@example.com");
        User jack = userDao.findByEmail("jack@example.com");

        questionDao.create(new Question("Answer:A Java Servlet is a server-side Java program that extends the capabilities of a web server by dynamically handling requests and responses. It is used to create web applications, generating dynamic content, and interacting with databases, APIs, or other services. Servlets run on a Java EE server and are a core component of Java's server-side technology.", true, "What is java servlet?", "servlet", alice.getId()));
        questionDao.create(new Question("Answer:Inheritance is the transfer of rights and obligations (inheritance) from a natural person", false, "What is inheritance in Java?", "inheritance", bob.getId()));
        questionDao.create(new Question("Answer:The Spring Framework is a comprehensive and modular framework for building enterprise-level Java applications. It provides infrastructure support for developing Java applications by offering features such as dependency injection, aspect-oriented programming, transaction management, and integration with various data access technologies. Spring promotes good design practices and makes it easier to manage application configuration and lifecycle.", true, "What is the Spring Framework?", "spring", charlie.getId()));
        questionDao.create(new Question("Answer:JDBC is JDBC :)", false, "What is JDBC in Java?", "jdbc", diana.getId()));
        questionDao.create(new Question("Answer:Collections in Java are a framework that provides an architecture for storing and manipulating groups of objects. They include interfaces, implementations (classes), and algorithms to help manage data efficiently. The main interfaces in the Java Collections Framework are List, Set, and Map, each designed for different types of data storage and retrieval needs. Common implementations include ArrayList, HashSet, and HashMap.", true, "What are collections in Java?", "collections", eve.getId()));
        questionDao.create(new Question("Answer:Multithreading in Java is the concurrent execution of two or more threads (small units of a process) within a single program to perform multiple tasks simultaneously. Java provides built-in support for multithreading through the Thread class and the Runnable interface, allowing developers to create and manage threads to improve the performance and responsiveness of applications.", true, "What is multithreading in Java?", "multithreading", frank.getId()));
        questionDao.create(new Question("Answer:The Streams API in Java is a powerful tool introduced in Java 8 that allows for functional-style operations on sequences of elements, such as collections. It enables developers to process data in a declarative way using a stream pipeline, consisting of source, intermediate operations (like filter, map), and terminal operations (like forEach, collect). This API supports parallel and sequential execution, making it easier to perform bulk operations on data efficiently and concisely.", true, "What is the Streams API in Java?", "streams", grace.getId()));
        questionDao.create(new Question("Answer:Exception handling is the process of responding to unwanted or unexpected events when a computer program runs.", false, "What is exception handling in Java?", "exceptions", hank.getId()));
        questionDao.create(new Question("Answer:Generics in Java to find out, go to my goble.dot page", false, "What are generics in Java?", "generics", ivy.getId()));
        questionDao.create(new Question("Answer:Lambda expressions in Java, introduced in Java 8, are a concise way to represent anonymous functions (i.e., methods without a declared name). They provide a clear and streamlined syntax for implementing functional interfaces (interfaces with a single abstract method). Lambda expressions enable functional programming techniques, making code more readable and expressive, especially when working with collections and the Streams API. For example, (int x, int y) -> x + y is a lambda expression that adds two integers.", true, "What are lambda expressions in Java?", "lambda", jack.getId()));
        questionDao.create(new Question("Answer:Java I/O (Input/Output) is a collection of classes and interfaces for reading and writing data (input and output) in Java applications. It supports handling of various data sources like files, network connections, memory buffers, and more.", true, "What is Java I/O?", "io", alice.getId()));
    }
}
