<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Tender</title>
    <link rel="stylesheet" href="css/addTender.css">
</head>
<body>
    <header>
        <h1>Add New Tender</h1>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="UserServlet">User Management</a></li>
                <li><a href="TenderServlet">Tender Management</a></li>
                <li><a href="BidServlet">Bid Management</a></li>
                <li><a href="reports.jsp">Reports and Analytics</a></li>
                <li><a href="notifications.jsp">Notifications and Alerts</a></li>
                <li><a href="settings.jsp">Settings</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <form action="TenderServlet?action=add" method="post">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" required>

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" required>

            <button type="submit">Add Tender</button>
        </form>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
