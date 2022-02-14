package by.epam.bartenderhelper.controller.command.impl.admin;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.*;
import by.epam.bartenderhelper.model.service.IngredientService;
import by.epam.bartenderhelper.model.service.impl.IngredientServiceImpl;
import by.epam.bartenderhelper.model.validator.IngredientFormValidator;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.RequestParameter.MESSAGE;
import static by.epam.bartenderhelper.controller.command.RequestParameter.OLD_PARAMETERS;
import static by.epam.bartenderhelper.controller.command.Router.RouterType.REDIRECT;


/**
 * The type Create ingredient command.
 */
public class CreateIngredientCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(request.getContextPath() + PagePath.GO_TO_ADD_INGREDIENT, REDIRECT);
        Map<String, String> parameters = RequestParameter.extractParameters(request);
        Optional<String> image = FileEncoder.encodeFile(request, RequestParameter.IMAGE);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        int message = MessageCode.INVALID_DATA;

        IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();
        if (validator.isCreateIngredientFormValid(parameters) && image.isPresent()) {
            try {
                long measureId = Long.parseLong(parameters.get(RequestParameter.MEASURE));
                BigDecimal price = new BigDecimal(parameters.get(RequestParameter.PRICE));
                String name = parameters.get(RequestParameter.NAME);
                String description = parameters.get(RequestParameter.INGREDIENT_DESCRIPTION);
                long calorie = Long.parseLong(parameters.get(RequestParameter.CALORIE));

                IngredientService service = IngredientServiceImpl.getInstance();
                if (service.isUniqueName(name)) {
                    Measure measure = new Measure(measureId);
                    Ingredient ingredient = new Ingredient.IngredientBuilder()
                            .description(description)
                            .name(name)
                            .calorie(calorie)
                            .measure(measure)
                            .verified(user.getRole() == UserRole.ADMIN)
                            .price(price)
                            .photo(new Photo.PhotoBuilder().name(name).data(image.get()).build())
                            .build();

                    service.createIngredient(ingredient);
                    router.setPage(request.getContextPath() + PagePath.SHOW_INGREDIENT.formatted(ingredient.getId()));
                    return router;
                } else {
                    message = MessageCode.NOT_UNIQUE_DATA;
                }
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }

        session.setAttribute(OLD_PARAMETERS, parameters);
        session.setAttribute(MESSAGE, message);
        return router;
    }
}
