package com.controller;

import com.model.Bid;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViewBidsServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tender_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bid> bids = new ArrayList<>();
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Get the user ID based on the username
        int userId = getUserIdByUsername(username);
        if (userId == -1) {
            request.setAttribute("errorMessage", "User not found.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Fetch bids for the user
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM bids WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int bidId = resultSet.getInt("bid_id");
                        int tenderId = resultSet.getInt("tender_id");
                        BigDecimal bidAmount = resultSet.getBigDecimal("bid_amount");
                        String bidStatus = resultSet.getString("bid_status");

                        Bid bid = new Bid(bidId, tenderId, userId, bidAmount, bidStatus);
                        bids.add(bid);

                        // Fetch tender title for each bid
                        String tenderTitle = getTenderTitleById(tenderId);
                        request.setAttribute("tenderTitle_" + tenderId, tenderTitle);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving bids.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("bids", bids);
        request.getRequestDispatcher("viewBids.jsp").forward(request, response);
    }

    private int getUserIdByUsername(String username) {
        int userId = -1;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT id FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("id");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    private String getTenderTitleById(int tenderId) {
        String title = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT title FROM tenders WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, tenderId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        title = resultSet.getString("title");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }
}
