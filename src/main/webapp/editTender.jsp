<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.Tender" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Tender</title>
    <link rel="stylesheet" href="css/addTender.css">
</head>
<body>
    <header>
        <h1>Edit Tender</h1>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="UserServlet">User Management</a></li>
                <li><a href="TenderServlet">Tender Management</a></li>
                <li><a href="BidServlet">Bid Management</a></li>
                <li><a href="reports.jsp">Reports and Analytics</a></li>
                <li><a href="notifications.jsp">Notifications and Alerts</a></li>
                <li><a href="settings.jsp">Settings</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <% 
            Tender tender = (Tender) request.getAttribute("tender");
            if (tender != null) {
        %>
        <form action="TenderServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= tender.getId() %>">

            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="<%= tender.getTitle() %>" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required><%= tender.getDescription() %></textarea>

            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" value="<%= tender.getStartDate() %>" required>

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" value="<%= tender.getEndDate() %>" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" value="<%= tender.getPrice() %>" required>

            <button type="submit">Update Tender</button>
        </form>
        <% 
            } else {
                out.println("Tender not found.");
            }
        %>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
