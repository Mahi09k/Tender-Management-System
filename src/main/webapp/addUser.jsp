<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New User</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Add New User</h1>
    </header>

    <main>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="add">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <label for="role">Role:</label>
            <select id="role" name="role">
                <option value="admin">Admin</option>
                <option value="customer">Customer</option>
                <option value="supplier">Supplier</option>
            </select>
            <button type="submit">Add User</button>
        </form>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
