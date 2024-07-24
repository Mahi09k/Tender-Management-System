<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Edit User</h1>
    </header>

    <main>
        <% 
            User user = (User) request.getAttribute("user");
            if (user != null) {
        %>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="userId" value="<%= user.getId() %>">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>
            <br>
            <label for="role">Role:</label>
            <input type="text" id="role" name="role" value="<%= user.getRole() %>" required>
            <br>
            <button type="submit">Update User</button>
        </form>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="userId" value="<%= user.getId() %>">
            <button type="submit">Delete User</button>
        </form>
        <% 
            } else {
                out.println("User not found.");
            }
        %>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
