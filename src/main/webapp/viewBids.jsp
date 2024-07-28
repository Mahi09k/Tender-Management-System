<%@ page import="java.util.List" %>
<%@ page import="com.model.Bid" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View My Bids</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <header>
        <h1>My Bids</h1>
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
                    <th>Tender Title</th>
                    <th>Bid Amount</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Bid> bids = (List<Bid>) request.getAttribute("bids");
                if (bids != null && !bids.isEmpty()) {
                    for (Bid bid : bids) { 
                        int tenderId = bid.getTenderId();
                        String tenderTitle = (String) request.getAttribute("tenderTitle_" + tenderId); // Retrieve tender title from request attributes
                %>
                <tr>
                    <td><%= tenderTitle %></td>
                    <td><%= bid.getBidAmount() %></td>
                    <td><%= bid.getBidStatus() %></td>
                </tr>
                <% 
                    } 
                } else { 
                %>
                <tr>
                    <td colspan="3">No bids available.</td>
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
