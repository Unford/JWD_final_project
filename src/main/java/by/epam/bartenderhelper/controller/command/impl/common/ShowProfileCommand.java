package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import by.epam.bartenderhelper.model.entity.dto.ReviewDto;
import by.epam.bartenderhelper.model.service.ReviewService;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.ReviewServiceImpl;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

/**
 * The type Show profile command.
 */
public class ShowProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setType(Router.RouterType.ERROR);

        String user = request.getParameter(RequestParameter.USER);
        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        User userProfile = null;

        try {
            if (user != null) {
                if (validator.isUserProfileValid(user)) {
                    UserService userService = UserServiceImpl.getInstance();
                    Optional<User> optionalUser = userService.findUserProfile(user);
                    if (optionalUser.isPresent()) {
                        userProfile = optionalUser.get();
                    }
                }
            } else {
                userProfile = currentUser.getRole() != UserRole.GUEST ? currentUser : null;
            }

            if (userProfile != null) {
                ReviewService reviewService = ReviewServiceImpl.getInstance();
                if (userProfile.getId() != currentUser.getId()){
                    Optional<Review> currentUserReview = reviewService.findUserReviewByAuthor(userProfile.getId(), currentUser.getId());
                    currentUserReview.ifPresent(review -> request.setAttribute(RequestParameter.MY_REVIEW, review));
                }
                List<ReviewDto> reviews = reviewService.findUserReviewsPart(userProfile.getId(), 1);

                request.setAttribute(RequestParameter.USER, userProfile);
                request.setAttribute(RequestParameter.REVIEWS, reviews);

                router.setPage(PagePath.PROFILE);
                router.setType(Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        return router;
    }
}
