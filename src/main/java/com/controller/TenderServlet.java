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
                    tenders.add(new Tender(id, title, description, startDate, endDate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tenders", tenders);
        request.getRequestDispatcher("tenderManagement.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tenderId = Integer.parseInt(request.getParameter("tenderId"));
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
                        tender = new Tender(tenderId, title, description, startDate, endDate);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tender", tender);
        request.getRequestDispatcher("editTender.jsp").forward(request, response);
    }

    private void deleteTender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tenderId = Integer.parseInt(request.getParameter("tenderId"));

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM tenders WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tenderId);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("TenderServlet");
    }
}
