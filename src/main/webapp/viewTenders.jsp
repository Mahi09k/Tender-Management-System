<%@ page import="java.util.List" %>
<%@ page import="com.model.Tender" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View My Tenders</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header>
        <h1>My Tenders</h1>
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
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Tender> tenders = (List<Tender>) request.getAttribute("tenders");
                if (tenders != null && !tenders.isEmpty()) {
                    for (Tender tender : tenders) { 
                %>
                <tr>
                    <td><%= tender.getTitle() %></td>
                    <td><%= tender.getDescription() %></td>
                    <td><%= tender.getStartDate() %></td>
                    <td><%= tender.getEndDate() %></td>
                    <td><%= tender.getPrice() %></td>
                </tr>
                <% 
                    } 
                } else { 
                %>
                <tr>
                    <td colspan="5">No tenders available.</td>
                </tr>
                <% 
                } 
                %>
            </tbody>
        </table>
    </main>

    <footer>
        <p>&copy; 2024 Tender Management System</p>
    </footer>
</body>
</html>
