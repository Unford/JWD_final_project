package by.epam.bartenderhelper.controller.command.impl;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class EditProfileCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {//todo
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        AtomicBoolean needName = new AtomicBoolean(true);
        AtomicReference<String> name = new AtomicReference<>();
        try {
            request.getParts().stream()
                    .filter(part -> part.getName().equals("avatar") && part.getSize() > 0)
                    .forEach(part -> {
                if (needName.compareAndSet(true, false)) {
                    name.set(part.getSubmittedFileName());

                }
                try {
                    part.getInputStream().transferTo(buffer);
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
       StringBuilder builder =  new StringBuilder();
        String ext = name.get().split("\\.")[1];
        builder.append("data:image/%s;base64, ".formatted(ext));
        byte[] encodeImg = Base64.encodeBase64(buffer.toByteArray(), false);
        String imgStr = StringUtils.newStringUtf8(encodeImg);
        builder.append(imgStr);
        request.setAttribute("img", builder.toString());
        return new Router(PagePath.MAIN, Router.RouterType.FORWARD);
    }
}
