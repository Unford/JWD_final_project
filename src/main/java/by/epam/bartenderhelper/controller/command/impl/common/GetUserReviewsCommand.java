package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.model.entity.Review;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.util.List;

public class GetUserReviewsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        var list = List.of(new Review.ReviewBuilder()//todo
                .reviewId(1).message("1")
                .score(3).timestamp(Instant.now())
                .authorId(1).build(),
                new Review.ReviewBuilder()
                        .reviewId(2).message("2")
                        .score(2).timestamp(Instant.now())
                        .authorId(3).build(), new Review.ReviewBuilder()
                        .reviewId(3).message("3")
                        .score(4.5).timestamp(Instant.now())
                        .authorId(3).build());
        request.setAttribute(RequestParameter.REVIEWS, list);
        return new Router(PagePath.REVIEW_AJAX, Router.RouterType.FORWARD);
    }
}
