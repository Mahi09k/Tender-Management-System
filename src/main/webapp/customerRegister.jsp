<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Customer Registration</h1>
    </header>
    <main>
        <form action="register" method="post">
            <input type="hidden" name="userType" value="customer">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Register</button>
        </form>
        <p>Already have an account? <a href="customerLogin.jsp">Login here</a></p>
        <% if(request.getParameter("error") != null) { %>
            <p style="color: red;"><%= request.getParameter("error") %></p>
        <% } %>
    </main>
    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
