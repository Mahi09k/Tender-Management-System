package com.controller;

import com.model.Tender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerTendersServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tender_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tender> tenders = new ArrayList<>();
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM tenders WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        String description = resultSet.getString("description");
                        String startDate = resultSet.getString("start_date");
                        String endDate = resultSet.getString("end_date");
                        double price = resultSet.getDouble("price");

                        Tender tender = new Tender(id, title, description, startDate, endDate, price);
                        tenders.add(tender);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving tenders.");
        }

        request.setAttribute("tenders", tenders);
        request.getRequestDispatcher("viewTenders.jsp").forward(request, response);
    }
}
