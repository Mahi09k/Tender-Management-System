<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Report</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Report</h1>
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
        <h2>Database Metadata and Report</h2>
        <table>
            <thead>
                <tr>
                    <th>Metric</th>
                    <th>Value</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Total Tenders</td>
                    <td><%= request.getAttribute("totalTenders") %></td>
                </tr>
                <tr>
                    <td>Database Product Name</td>
                    <td><%= request.getAttribute("databaseProductName") %></td>
                </tr>
                <tr>
                    <td>Database Product Version</td>
                    <td><%= request.getAttribute("databaseProductVersion") %></td>
                </tr>
                <tr>
                    <td>Driver Name</td>
                    <td><%= request.getAttribute("driverName") %></td>
                </tr>
                <tr>
                    <td>Driver Version</td>
                    <td><%= request.getAttribute("driverVersion") %></td>
                </tr>
            </tbody>
        </table>
    </main>
</body>
</html>
