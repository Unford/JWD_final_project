package by.epam.bartenderhelper.controller.command.impl.auth;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Photo;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;
import static by.epam.bartenderhelper.controller.command.SessionAttribute.USER;


/**
 * The type Edit profile command.
 */
public class EditProfileCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> parameters = extractParameters(request);
        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        Optional<String> avatar = FileEncoder.encodeFile(request, AVATAR);
        int message = MessageCode.INVALID_USER_DATA;
        HttpSession session = request.getSession();

        if (validator.isFormUpdateValid(parameters)) {
            UserService service = UserServiceImpl.getInstance();
            User currentUser = (User) session.getAttribute(USER);

            Photo photo = new Photo.PhotoBuilder()
                    .photoId(currentUser.getPhoto().getId())
                    .name(currentUser.getUsername())
                    .data(avatar.orElse(null))
                    .build();

            User userUpdate = new User.UserBuilder()
                    .userId(currentUser.getId())
                    .description(parameters.get(USER_DESCRIPTION))
                    .firstName(parameters.get(FIRST_NAME))
                    .lastName(parameters.get(LAST_NAME))
                    .photo(photo)
                    .build();

            try {
                if (service.updateUser(userUpdate)){
                    currentUser.setDescription(parameters.get(USER_DESCRIPTION));
                    currentUser.setFirstName(parameters.get(FIRST_NAME));
                    currentUser.setLastName(parameters.get(LAST_NAME));
                    currentUser.setPhoto(photo.getData() == null ? currentUser.getPhoto() : photo);
                    message = MessageCode.UPDATE_SUCCESS;
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }

        session.setAttribute(OLD_PARAMETERS, parameters);
        session.setAttribute(RequestParameter.MESSAGE, message);
        return new Router(request.getContextPath() + PagePath.GO_TO_EDIT_PROFILE, Router.RouterType.REDIRECT);

    }
}
