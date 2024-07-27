<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.Bid" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Bid</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Add Bid</h1>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="UserServlet">User Management</a></li>
                <li><a href="TenderServlet">Tender Management</a></li>
                <li><a href="ReportServlet">Reports and Analytics</a></li>
                <li><a href="notifications.jsp">Notifications and Alerts</a></li>
                <li><a href="index.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>Submit New Bid</h2>
        <form action="BidServlet" method="post">
            <input type="hidden" name="action" value="add">
            <label for="tenderId">Tender ID:</label>
            <input type="number" id="tenderId" name="tenderId" required>
            <br>
            <label for="userId">User ID:</label>
            <input type="number" id="userId" name="userId" required>
            <br>
            <label for="bidAmount">Bid Amount:</label>
            <input type="number" id="bidAmount" name="bidAmount" step="0.01" required>
            <br>
            <label for="bidStatus">Bid Status:</label>
            <input type="text" id="bidStatus" name="bidStatus" required>
            <br>
            <button type="submit">Add Bid</button>
        </form>
        <a href="BidServlet">Back to Bid Management</a>
    </main>
    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
