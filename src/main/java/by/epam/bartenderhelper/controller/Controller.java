package by.epam.bartenderhelper.controller;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.CommandType;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Controller.
 */
@WebServlet(urlPatterns = {"/controller"})
@MultipartConfig(maxFileSize = 5 * 1024 * 1024,
        fileSizeThreshold = 1024 * 1024,
        maxRequestSize = 25 * 1024 * 1024)
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//todo
        String stringCommand = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandType.defineCommand(stringCommand);
        Router router = null;
        try {
            router = command.execute(request);
        } catch (CommandException e) {
            response.sendError(500, e.getMessage());
        }
        switch (router.getType()) {
            case FORWARD -> {
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPage());
                dispatcher.forward(request, response);
            }
            case REDIRECT -> response.sendRedirect(router.getPage());

            case ERROR -> response.sendError(router.getErrorCode());

            default -> response.sendError(404);
        }
    }
}
