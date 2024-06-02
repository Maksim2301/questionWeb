<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ask a New Question</title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h2>Ask a New Question</h2>
    <form action="questions" method="post">
        <input type="hidden" name="command" value="create">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br>
        <label for="question">Answer:</label>
        <textarea id="question" name="question"></textarea><br>
        <label for="keywords">Keywords:</label>
        <input type="text" id="keywords" name="keywords"><br>
        <label for="answered">Answered:</label>
        <input type="checkbox" id="answered" name="answered"><br>
        <input type="submit" value="Submit">
    </form>
    
    <form action="questions" method="get">
        <input type="hidden" name="command" value="showDashboard">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
