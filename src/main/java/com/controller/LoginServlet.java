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
import javax.servlet.http.HttpSession;

import com.model.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (validateUser(username, password, userType)) {
            // Create or get the existing session
            HttpSession session = request.getSession();
            // Set the username in the session
            session.setAttribute("username", username);

            // Redirect to the appropriate dashboard based on user type
            if ("admin".equals(userType)) {
                response.sendRedirect("adminDashboard.jsp");
            } else if ("customer".equals(userType)) {
                response.sendRedirect("customerDashboard.jsp");
            } else if ("supplier".equals(userType)) {
                response.sendRedirect("supplierDashboard.jsp");
            }
        } else {
            // Redirect back to login page with error message
            response.sendRedirect(userType + "Login.jsp?error=Invalid%20credentials");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            String userType = request.getParameter("userType");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (registerUser(username, password, userType)) {
                response.sendRedirect(userType + "Login.jsp?message=Registration%20successful");
            } else {
                response.sendRedirect(userType + "Register.jsp?error=Registration%20failed");
            }
        }
    }

    private boolean validateUser(String username, String password, String role) {
        boolean isValid = false;
        Connection connection = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isValid = true;
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
        return isValid;
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
            int result = preparedStatement.executeUpdate();
            isRegistered = (result > 0);
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
