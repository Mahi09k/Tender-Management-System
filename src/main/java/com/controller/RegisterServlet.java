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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (registerUser(username, password, userType)) {
            if ("customer".equals(userType)) {
                response.sendRedirect("customerDashboard.jsp");
            } else if ("supplier".equals(userType)) {
                response.sendRedirect("supplierDashboard.jsp");
            }
        } else {
            response.sendRedirect(userType + "Register.jsp?error=Registration%20failed");
        }
    }

    private boolean registerUser(String username, String password, String role) {
        boolean isRegistered = false;
        Connection connection = DBConnection.getConnection();
        try {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isRegistered = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isRegistered;
    }
}
