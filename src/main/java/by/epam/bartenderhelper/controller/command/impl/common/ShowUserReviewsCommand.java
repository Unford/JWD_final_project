package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.dto.ReviewDto;
import by.epam.bartenderhelper.model.service.ReviewService;
import by.epam.bartenderhelper.model.service.impl.ReviewServiceImpl;
import by.epam.bartenderhelper.model.validator.ReviewFormValidator;
import by.epam.bartenderhelper.model.validator.impl.AbstractFormValidator;
import by.epam.bartenderhelper.model.validator.impl.ReviewFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * The type Get user reviews command.
 */
public class ShowUserReviewsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.REVIEW_AJAX, Router.RouterType.FORWARD);

        String userIdText = request.getParameter(RequestParameter.USER);
        String pageText = request.getParameter(RequestParameter.PAGINATION_PAGE);
        ReviewFormValidator validator = ReviewFormValidatorImpl.getInstance();

        if (validator.isIntegerValid(pageText) && validator.isIntegerValid(userIdText)) {
            ReviewService service = ReviewServiceImpl.getInstance();
            try {
                long userId = Long.parseLong(userIdText);
                int page = Integer.parseInt(pageText);
                List<ReviewDto> reviews = service.findUserReviewsPart(userId, page);
                request.setAttribute(RequestParameter.REVIEWS, reviews);
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }else {
            router.setType(Router.RouterType.ERROR);
            router.setErrorCode(500);
        }

        return router;
    }
}
