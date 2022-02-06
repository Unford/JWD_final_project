package by.epam.bartenderhelper.controller.command.impl.auth;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.ReviewService;
import by.epam.bartenderhelper.model.service.impl.ReviewServiceImpl;
import by.epam.bartenderhelper.model.validator.ReviewFormValidator;
import by.epam.bartenderhelper.model.validator.impl.ReviewFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.Instant;
import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

/**
 * The type Send review command.
 */
public class SendReviewCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> parameters = extractParameters(request);
        ReviewFormValidator validator = ReviewFormValidatorImpl.getInstance();
        HttpSession session = request.getSession();

        if (validator.isReviewFormValid(parameters)) {
            try {
                long userId = Long.parseLong(parameters.get(USER));
                double score = Double.parseDouble(parameters.get(RATING));
                String message = parameters.get(REVIEW_MESSAGE);

                ReviewService service = ReviewServiceImpl.getInstance();
                User currentUser = (User) session.getAttribute(SessionAttribute.USER);
                if (userId != currentUser.getId() && service.checkUserReview(userId, currentUser.getId())) {
                    Review review = new Review.ReviewBuilder()
                            .message(message)
                            .score(score)
                            .timestamp(Instant.now())
                            .authorId(currentUser.getId())
                            .build();
                    service.createUserReview(review, userId);
                }
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }

        return PagePath.getPreviousPage(request);
    }
}
