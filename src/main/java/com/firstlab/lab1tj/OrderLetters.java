package com.firstlab.lab1tj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eliza
 */
@WebServlet(name = "OrderLetters", urlPatterns = {"/OrderLetters"})
public class OrderLetters extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String parameter = request.getParameter("name");
        response(response, parameter);
    }

    private void response(HttpServletResponse response, String message)
            throws IOException {
        PrintWriter out = response.getWriter();
        char[] messageArray = message.toLowerCase().toCharArray();
        Arrays.sort(messageArray);
        out.println("<html>");
        out.println("<body>");
        out.println("<t1> " + String.valueOf(messageArray) + "</t1>");
        out.println("</body>");
        out.println("</html>");
    }
}
