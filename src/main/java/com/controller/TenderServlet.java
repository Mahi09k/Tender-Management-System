package com.controller;

import java.io.IOException;
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
import com.model.DBConnection;
import com.model.Tender;

@WebServlet("/TenderServlet")
public class TenderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            listTenders(request, response);
        } else if ("edit".equals(action)) {
            showEditForm(request, response);
        } else if ("delete".equals(action)) {
            deleteTender(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addTender(request, response);
        } else if ("update".equals(action)) {
            updateTender(request, response);
        }
    }

    private void listTenders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tender> tenders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tenders";
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String startDate = resultSet.getString("start_date");
                    String endDate = resultSet.getString("end_date");
                    double price = resultSet.getDouble("price");
                    tenders.add(new Tender(id, title, description, startDate, endDate, price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tenders", tenders);
        request.getRequestDispatcher("tenderManagement.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenderIdParam = request.getParameter("tenderId");
        int tenderId = 0;
        try {
            tenderId = Integer.parseInt(tenderIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid tender ID.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }

        Tender tender = null;

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tenders WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tenderId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String title = resultSet.getString("title");
                        String description = resultSet.getString("description");
                        String startDate = resultSet.getString("start_date");
                        String endDate = resultSet.getString("end_date");
                        double price = resultSet.getDouble("price");
                        tender = new Tender(tenderId, title, description, startDate, endDate, price);
                    } else {
                        request.setAttribute("errorMessage", "Tender not found.");
                        request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tender", tender);
        request.getRequestDispatcher("editTender.jsp").forward(request, response);
    }

    private void addTender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        double price = Double.parseDouble(request.getParameter("price"));

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO tenders (title, description, start_date, end_date, price) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, description);
                statement.setString(3, startDate);
                statement.setString(4, endDate);
                statement.setDouble(5, price);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("TenderServlet");
    }

    private void updateTender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        double price = Double.parseDouble(request.getParameter("price"));

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE tenders SET title=?, description=?, start_date=?, end_date=?, price=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, description);
                statement.setString(3, startDate);
                statement.setString(4, endDate);
                statement.setDouble(5, price);
                statement.setInt(6, id);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("TenderServlet");
    }

    private void deleteTender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tenderId = Integer.parseInt(request.getParameter("tenderId"));

        System.out.println("Attempting to delete tender with ID: " + tenderId); // Debugging statement

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM tenders WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tenderId);
                int rowsAffected = statement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected); // Debugging statement

                if (rowsAffected == 0) {
                    System.out.println("No tender found with ID: " + tenderId); // Debugging statement
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }

        response.sendRedirect("TenderServlet");
    }
}
