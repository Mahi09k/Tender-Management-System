package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DBConnection;

@WebServlet("/AddTenderServlet")
public class AddTenderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        double price = Double.parseDouble(request.getParameter("price"));

        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();

            // Query to insert a new tender into the database
            String sql = "INSERT INTO tenders (title, description, start_date, end_date, price, username) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, username); // Assuming you store the username with the tender

            preparedStatement.executeUpdate();

            // Redirect to the page showing the user's tenders
            response.sendRedirect("customerDashboard");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exceptions
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } finally {
            // Close resources in the finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
