<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h2>Welcome, ${sessionScope.user.name}</h2>
    <h3>Your Questions</h3>
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
                        <td>${sessionScope.user.name}</td>
                        <td>
                            <form action="questions" method="GET" style="display:inline;">
                                   <input type="hidden" name="command" value="edit">
                                   <input type="hidden" name="id" value="${question.id}">
                                   <input type="submit" value="edit">
                            </form>
                                   <p>----------</p>
                            <form action="questions" method="post" style="display:inline;">
                                <input type="hidden" name="command" value="delete">
                                <input type="hidden" name="id" value="${question.id}">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <form action="questions" method="get">
        <input type="hidden" name="command" value="createForm">
        <input type="submit" value="Ask a new question">
    </form>
    
    <form action="questions" method="get">
        <input type="hidden" name="command" value="showAllQuestions">
        <input type="submit" value="View All Questions">
    </form>
</body>
</html>
