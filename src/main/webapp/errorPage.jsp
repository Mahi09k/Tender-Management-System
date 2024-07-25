<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Error</h1>
    </header>
    <main>
        <p><%= request.getAttribute("errorMessage") %></p>
        <a href="adminDashboard.jsp">Back to Dashboard</a>
    </main>
    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
