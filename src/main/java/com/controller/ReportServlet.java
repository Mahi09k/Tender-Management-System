package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.DBConnection;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();

            // Query to get the total number of tenders
            String sql = "SELECT COUNT(*) AS totalTenders FROM tenders";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            int totalTenders = 0;
            if (resultSet.next()) {
                totalTenders = resultSet.getInt("totalTenders");
            }

            // Get database metadata
            java.sql.DatabaseMetaData metaData = connection.getMetaData();
            String databaseProductName = metaData.getDatabaseProductName();
            String databaseProductVersion = metaData.getDatabaseProductVersion();
            String driverName = metaData.getDriverName();
            String driverVersion = metaData.getDriverVersion();

            // Set attributes for the JSP page
            request.setAttribute("totalTenders", totalTenders);
            request.setAttribute("databaseProductName", databaseProductName);
            request.setAttribute("databaseProductVersion", databaseProductVersion);
            request.setAttribute("driverName", driverName);
            request.setAttribute("driverVersion", driverVersion);

            // Forward to JSP
            request.getRequestDispatcher("report.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        } finally {
            // Clean up resources
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
