<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Tender" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tender Management</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Tender Management</h1>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="UserServlet">User Management</a></li>
                <li><a href="tenderManagement.jsp">Tender Management</a></li>
                <li><a href="bidManagement.jsp">Bid Management</a></li>
                <li><a href="reports.jsp">Reports and Analytics</a></li>
                <li><a href="notifications.jsp">Notifications and Alerts</a></li>
                <li><a href="settings.jsp">Settings</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>All Tenders</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Price</th> <!-- Added price column -->
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Object tendersObj = request.getAttribute("tenders");
                    if (tendersObj instanceof List<?>) {
                        List<Tender> tenders = (ArrayList<Tender>) tendersObj;
                        if (!tenders.isEmpty()) {
                            for (Tender tender : tenders) {
                %>
                    <tr>
                        <td><%= tender.getId() %></td>
                        <td><%= tender.getTitle() %></td>
                        <td><%= tender.getDescription() %></td>
                        <td><%= tender.getStartDate() %></td>
                        <td><%= tender.getEndDate() %></td>
                        <td><%= tender.getPrice() %></td> <!-- Added price field -->
                        <td>
                            <a href="TenderServlet?action=edit&tenderId=<%= tender.getId() %>">Edit</a>
                            <a href="TenderServlet?action=delete&tenderId=<%= tender.getId() %>">Delete</a>
                        </td>
                    </tr>
                <% 
                            }
                        } else {
                %>
                    <tr>
                        <td colspan="7">No tenders found.</td> <!-- Adjusted colspan -->
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7">No tenders found.</td> <!-- Adjusted colspan -->
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
        <a href="addTender.jsp">Add New Tender</a>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
