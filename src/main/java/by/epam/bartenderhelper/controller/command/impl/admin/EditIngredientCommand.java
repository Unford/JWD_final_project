package by.epam.bartenderhelper.controller.command.impl.admin;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;
import by.epam.bartenderhelper.model.entity.Photo;
import by.epam.bartenderhelper.model.service.IngredientService;
import by.epam.bartenderhelper.model.service.impl.IngredientServiceImpl;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.RequestParameter.MESSAGE;

/**
 * The type Edit ingredient command.
 */
public class EditIngredientCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> parameters = RequestParameter.extractParameters(request);
        Optional<String> image = FileEncoder.encodeFile(request, RequestParameter.IMAGE);
        HttpSession session = request.getSession();
        int message = MessageCode.INVALID_DATA;


        IngredientFormValidatorImpl validator = IngredientFormValidatorImpl.getInstance();
        if (validator.isEditIngredientFormValid(parameters)) {
            try {
                long ingredientId = Long.parseLong(parameters.get(RequestParameter.INGREDIENT));
                long measureId = Long.parseLong(parameters.get(RequestParameter.MEASURE));
                BigDecimal price = new BigDecimal(parameters.get(RequestParameter.PRICE));
                String name = parameters.get(RequestParameter.NAME);
                String description = parameters.get(RequestParameter.INGREDIENT_DESCRIPTION);
                long calorie = Long.parseLong(parameters.get(RequestParameter.CALORIE));

                IngredientService service = IngredientServiceImpl.getInstance();
                if (service.isUniqueName(name, ingredientId)) {
                    Measure measure = new Measure(measureId);
                    Ingredient ingredient = new Ingredient.IngredientBuilder()
                            .ingredientId(ingredientId)
                            .description(description)
                            .name(name)
                            .calorie(calorie)
                            .measure(measure)
                            .price(price)
                            .photo(new Photo.PhotoBuilder()
                                    .name(name)
                                    .data(image.orElse(null))
                                    .build())
                            .build();
                    service.updateIngredient(ingredient);
                    message = MessageCode.UPDATE_SUCCESS;

                }else {
                    message = MessageCode.NOT_UNIQUE_DATA;
                }
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        session.setAttribute(MESSAGE, message);
        return PagePath.getPreviousPage(request);
    }
}
