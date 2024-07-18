package arith;

package com.example.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/arithmetic")
public class ArithmeticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String operation = request.getParameter("operation");

        // Convert parameters to integers
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        // Perform arithmetic operation
        int result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    // Handle division by zero error
                    request.setAttribute("error", "Cannot divide by zero");
                    request.getRequestDispatcher("/WEB-INF/views/arithmetic.jsp").forward(request, response);
                    return;
                }
                break;
            default:
                // Handle invalid operation
                request.setAttribute("error", "Invalid operation");
                request.getRequestDispatcher("/WEB-INF/views/arithmetic.jsp").forward(request, response);
                return;
        }

        // Forward result to result.jsp
        request.setAttribute("result", result);
        request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
    }
}

