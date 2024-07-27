<!DOCTYPE html>
<html>
<head>
    <title>Supplier Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <form action="login" method="post">
        <input type="hidden" name="userType" value="supplier">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Login</button>
    </form>
</body>
</html>
