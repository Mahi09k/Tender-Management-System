<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Bid" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bid Management</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Bid Management</h1>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="UserServlet">User Management</a></li>
                <li><a href="TenderServlet">Tender Management</a></li>
                <li><a href="BidServlet">Bid Management</a></li>
                <li><a href="reports.jsp">Reports and Analytics</a></li>
                <li><a href="notifications.jsp">Notifications and Alerts</a></li>
                <li><a href="settings.jsp">Settings</a></li>
                <li><a href="index.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>All Bids</h2>
        <table>
            <thead>
                <tr>
                    <th>Bid ID</th>
                    <th>Tender ID</th>
                    <th>User ID</th>
                    <th>Bid Amount</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Bid> bids = (List<Bid>) request.getAttribute("bids");
                    if (bids != null && !bids.isEmpty()) {
                        for (Bid bid : bids) {
                %>
                    <tr>
                        <td><%= bid.getBidId() %></td>
                        <td><%= bid.getTenderId() %></td>
                        <td><%= bid.getUserId() %></td>
                        <td><%= bid.getBidAmount() %></td>
                        <td><%= bid.getBidStatus() %></td>
                        <td>
                            <a href="BidServlet?action=edit&bidId=<%= bid.getBidId() %>">Edit</a>
                            <a href="BidServlet?action=delete&bidId=<%= bid.getBidId() %>">Delete</a>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="6">No bids found.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
        <a href="addBid.jsp">Submit New Bid</a>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
