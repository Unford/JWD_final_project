package by.epam.bartenderhelper.controller.command.impl.auth;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.ReviewService;
import by.epam.bartenderhelper.model.service.impl.ReviewServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Delete user review command.
 */
public class DeleteUserReviewCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String userIdText = request.getParameter(RequestParameter.USER);

        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        if (validator.isIdValid(userIdText)) {
            try {
                ReviewService service = ReviewServiceImpl.getInstance();
                long userid = Long.parseLong(userIdText);
                if (!service.isUniqueUserReview(userid, currentUser.getId())){
                    service.deleteUserReviewByAuthor(userid, currentUser.getId());
                }
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        return PagePath.getPreviousPage(request);
    }
}
