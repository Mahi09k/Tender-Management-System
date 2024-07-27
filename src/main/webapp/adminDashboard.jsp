<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/admindashboard.css">
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Dashboard</a></li>
                <li><a href="UserServlet">User Management</a></li>
                <li><a href="TenderServlet">Tender Management</a></li>
                <li><a href="BidServlet">Bid Management</a></li>
                <li><a href="ReportServlet">Reports and Analytics</a></li>
                <li><a href="notifications.jsp">Notifications and Alerts</a></li>
                <li><a href="settings.jsp">Settings</a></li>
                <li><a href="index.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section id="overview">
            <h2>Overview</h2>
            <div class="metrics">
                <div class="metric">
                    <h3>Total Tenders</h3>
                    <p>
                        <%
                            // Replace the following with actual code to fetch total tenders from the database
                            int totalTenders = 10; // Example value
                            out.print(totalTenders);
                        %>
                    </p>
                </div>
                <div class="metric">
                    <h3>Pending Bids</h3>
                    <p>
                        <%
                            // Replace the following with actual code to fetch pending bids from the database
                            int pendingBids = 5; // Example value
                            out.print(pendingBids);
                        %>
                    </p>
                </div>
                <div class="metric">
                    <h3>New Notifications</h3>
                    <p>
                        <%
                            // Replace the following with actual code to fetch new notifications from the database
                            int newNotifications = 3; // Example value
                            out.print(newNotifications);
                        %>
                    </p>
                </div>
            </div>
        </section>

        <section id="quick-actions">
            <h2>Quick Actions</h2>
            <ul>
                <li><a href="addTender.jsp">Create New Tender</a></li>
                <li><a href="UserServlet">Manage Users</a></li>
                <li><a href="viewReports.jsp">View Reports</a></li>
                <li><a href="sendNotification.jsp">Send Notification</a></li>
            </ul>
        </section>

        <!-- Additional sections can be added here as needed -->
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
