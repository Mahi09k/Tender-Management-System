<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, com.model.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Customer Dashboard</h1>
        <nav>
            <ul>
                <!-- Move Customer Dashboard link to the front -->
                <li><a href="customerDashboard">Customer Dashboard</a></li>
                <li><a href="createTender.jsp">Create Tender</a></li>
                <li><a href="CustomerTendersServlet">View My Tenders</a></li>
                <li><a href="ViewBidsServlet">View My Bids</a></li>
                <li><a href="index.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>Welcome, <%= request.getSession().getAttribute("username") %>!</h2>
        
        <h3>Your Profile:</h3>
        <%
            String username = (String) request.getSession().getAttribute("username");
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
        %>
            <p><strong>Username:</strong> <%= resultSet.getString("username") %></p>
            <p><strong>Role:</strong> <%= resultSet.getString("role") %></p>
        <% 
            }
            connection.close();
        %>
    </main>
    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
