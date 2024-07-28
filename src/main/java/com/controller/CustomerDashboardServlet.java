package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DBConnection;
import com.model.Tender;

@WebServlet("/customerDashboard")
public class CustomerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Tender> tenders = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();

            // Query to get the user details
            String queryUser = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(queryUser);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("user", resultSet);
            } else {
                request.setAttribute("errorMessage", "User not found");
            }

            // Query to get the tenders created by the user
            String queryTenders = "SELECT * FROM tenders WHERE username = ?";
            preparedStatement = connection.prepareStatement(queryTenders);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                double price = resultSet.getDouble("price");
                tenders.add(new Tender(id, title, description, startDate, endDate, price));
            }

            request.setAttribute("tenders", tenders);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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

        request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
    }
}
