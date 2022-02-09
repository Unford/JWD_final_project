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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.RequestParameter.OLD_PARAMETERS;


public class CreateIngredientCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.ADD_INGREDIENT, Router.RouterType.FORWARD);
        Map<String, String> parameters = RequestParameter.extractParameters(request);
        Optional<String> image = FileEncoder.encodeFile(request, RequestParameter.IMAGE);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);

        IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();
        if (validator.isCreateIngredientFormValid(parameters) && image.isPresent()) {
            try {
                long measureId = Long.parseLong(parameters.get(RequestParameter.MEASURE));
                BigDecimal price = new BigDecimal(parameters.get(RequestParameter.PRICE));
                String name = parameters.get(RequestParameter.NAME);
                String description = parameters.get(RequestParameter.INGREDIENT_DESCRIPTION);
                long calorie = Long.parseLong(parameters.get(RequestParameter.CALORIE));
                IngredientService service = IngredientServiceImpl.getInstance();
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
                router.setPage(PagePath.SHOW_INGREDIENT.formatted(ingredient.getId()));
                router.setType(Router.RouterType.REDIRECT);
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        if (router.getType() == Router.RouterType.FORWARD){
            IngredientServiceImpl service = IngredientServiceImpl.getInstance();
            List<Measure> measures;
            try {
                measures = service.findAllMeasures();
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
            request.setAttribute(RequestParameter.MEASURES, measures);
            request.setAttribute(OLD_PARAMETERS, parameters);
        }

        return router;
    }
}
