<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.Bid" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Bid</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Edit Bid</h1>
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
        <h2>Edit Bid</h2>
        <% 
            Bid bid = (Bid) request.getAttribute("bid");
            if (bid != null) {
        %>
            <form action="BidServlet" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="bidId" value="<%= bid.getBidId() %>">
                <label for="tenderId">Tender ID:</label>
                <input type="number" id="tenderId" name="tenderId" value="<%= bid.getTenderId() %>" required>
                <br>
                <label for="userId">User ID:</label>
                <input type="number" id="userId" name="userId" value="<%= bid.getUserId() %>" required>
                <br>
                <label for="bidAmount">Bid Amount:</label>
                <input type="number" id="bidAmount" name="bidAmount" value="<%= bid.getBidAmount() %>" step="0.01" required>
                <br>
                <label for="bidStatus">Bid Status:</label>
                <input type="text" id="bidStatus" name="bidStatus" value="<%= bid.getBidStatus() %>" required>
                <br>
                <button type="submit">Update Bid</button>
            </form>
        <% 
            } else { 
        %>
            <p>Bid not found.</p>
        <% 
            } 
        %>
        <a href="BidServlet">Back to Bid Management</a>
    </main>
    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
