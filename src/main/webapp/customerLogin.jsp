<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Customer Login</h1>
    </header>
    <main>
        <form action="login" method="post">
            <input type="hidden" name="userType" value="customer">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="customerRegister.jsp">Register here</a></p>
    </main>
    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
