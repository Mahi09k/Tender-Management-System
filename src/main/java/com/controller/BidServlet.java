package com.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Bid;
import com.model.DBConnection;

@WebServlet("/BidServlet")
public class BidServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            listBids(request, response);
        } else if ("edit".equals(action)) {
            showEditForm(request, response);
        } else if ("delete".equals(action)) {
            deleteBid(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addBid(request, response);
        } else if ("edit".equals(action)) {
            updateBid(request, response);
        }
    }

    private void listBids(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bid> bids = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bids";
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int bidId = resultSet.getInt("bid_id");
                    int tenderId = resultSet.getInt("tender_id");
                    int userId = resultSet.getInt("user_id");
                    BigDecimal bidAmount = resultSet.getBigDecimal("bid_amount");
                    String bidStatus = resultSet.getString("bid_status");
                    bids.add(new Bid(bidId, tenderId, userId, bidAmount, bidStatus));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("bids", bids);
        request.getRequestDispatcher("bidManagement.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        Bid bid = null;

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bids WHERE bid_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int tenderId = resultSet.getInt("tender_id");
                        int userId = resultSet.getInt("user_id");
                        BigDecimal bidAmount = resultSet.getBigDecimal("bid_amount");
                        String bidStatus = resultSet.getString("bid_status");
                        bid = new Bid(bidId, tenderId, userId, bidAmount, bidStatus);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("bid", bid);
        request.getRequestDispatcher("editBid.jsp").forward(request, response);
    }

    private void deleteBid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bidId = Integer.parseInt(request.getParameter("bidId"));

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM bids WHERE bid_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidId);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("BidServlet");
    }
    
    private void addBid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tenderId = Integer.parseInt(request.getParameter("tenderId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        BigDecimal bidAmount = new BigDecimal(request.getParameter("bidAmount"));
        String bidStatus = request.getParameter("bidStatus");

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO bids (tender_id, user_id, bid_amount, bid_status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tenderId);
                statement.setInt(2, userId);
                statement.setBigDecimal(3, bidAmount);
                statement.setString(4, bidStatus);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("BidServlet");
    }

    private void updateBid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        int tenderId = Integer.parseInt(request.getParameter("tenderId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        BigDecimal bidAmount = new BigDecimal(request.getParameter("bidAmount"));
        String bidStatus = request.getParameter("bidStatus");

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE bids SET tender_id=?, user_id=?, bid_amount=?, bid_status=? WHERE bid_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tenderId);
                statement.setInt(2, userId);
                statement.setBigDecimal(3, bidAmount);
                statement.setString(4, bidStatus);
                statement.setInt(5, bidId);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("BidServlet");
    }
}
