package by.epam.bartenderhelper.controller.command.impl.admin;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.model.entity.Cocktail;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreateCocktailCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> parameters = RequestParameter.extractParameters(request);
        Optional<String> image = FileEncoder.encodeFile(request, RequestParameter.IMAGE);

        Map<String, String> ingredientParameters = new HashMap<>();
        for (int i = 1; i <= (parameters.size() - 4) / 2; i++) {
            String ingredientId = parameters.get(RequestParameter.INGREDIENT + i);
            String ingredientAmount = parameters.get(RequestParameter.AMOUNT + i);
            ingredientParameters.put(ingredientId, ingredientAmount);
        }

        return PagePath.getPreviousPage(request);
    }
}
