<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Question</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h2>Edit Question</h2>
    <form action="questions" method="post">
        <input type="hidden" name="command" value="save">
        <input type="hidden" name="id" value="${questions.id}">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${questions.title}" required><br>
        <label for="question">Answer:</label>
        <textarea id="question" name="question">${questions.question}</textarea><br>
        <label for="answered">Answered:</label>
        <input type="checkbox" id="answered" name="answered" ${questions.answered ? 'checked' : ''}><br>
        <label for="keywords">Keywords:</label>
        <input type="text" id="keywords" name="keywords" value="${questions.keywords}"><br>
        <input type="submit" value="Save">
    </form>
        
    <form action="questions" method="get">
        <input type="hidden" name="command" value="showDashboard">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
