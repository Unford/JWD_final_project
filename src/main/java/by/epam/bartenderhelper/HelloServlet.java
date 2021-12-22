package by.epam.bartenderhelper;

import java.io.*;
import java.sql.Connection;

import by.epam.bartenderhelper.db.ConnectionPool;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    static Logger logger = LogManager.getLogger();
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        logger.log(Level.INFO, "Hello logger");
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ConnectionPool.getInstance().destroyPool();
        ConnectionPool.getInstance().releaseConnection(connection);
        Connection connection1 = ConnectionPool.getInstance().takeConnection();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}