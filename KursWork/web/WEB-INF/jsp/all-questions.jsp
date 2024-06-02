<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Questions</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h2>All Questions</h2>

    <form action="questions" method="get">
        <input type="hidden" name="command" value="search">
        <input type="text" name="keyword" placeholder="Enter keywords">
        <input type="submit" value="Search">
    </form>
    
    <form action="questions" method="get">
        <input type="hidden" name="command" value="showDashboard">
        <input type="submit" value="Back to Dashboard">
    </form>
    
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <c:if test="${empty questions}">
        <p>No questions available</p>
    </c:if>
    <c:if test="${not empty questions}">
        <table border="1">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Answer</th>
                    <th>Answered</th>
                    <th>Keywords</th>
                    <th>User</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="question" items="${questions}">
                    <tr>
                        <td>${question.title}</td>
                        <td>${question.question}</td>
                        <td>${question.answered}</td>
                        <td>${question.keywords}</td>
                        <td>${userNames[question.userId]}</td>
                        <td>
                            <form action="questions" method="post">
                                <input type="hidden" name="command" value="addAnswer">
                                <input type="hidden" name="questionId" value="${question.id}">
                                <textarea name="answer" rows="4" cols="50" placeholder="Enter your answer"></textarea>
                                <br>
                                <input type="submit" value="Submit Answer">
                            </form>
                        </td>
                    </tr>
                    <c:if test="${not empty question.answers}">
                        <tr>
                            <td colspan="6">
                                <strong>Answers:</strong>
                                <ul>
                                    <c:forEach var="answer" items="${question.answers}">
                                        <li>${answer}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
