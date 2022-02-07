package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type File encoder.
 */
public final class FileEncoder {
    private static final Logger logger = LogManager.getLogger();

    private static final String BASE_64_FILE = "data:%s;base64, %s";

    /**
     * Encode file optional.
     *
     * @param request  the request
     * @param filename the filename
     * @return the optional
     */
    public static Optional<String> encodeFile(HttpServletRequest request, String filename) {
        Optional<String> encodeFile = Optional.empty();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        AtomicReference<String> contentType = new AtomicReference<>();
        try {
            request.getParts().stream()
                    .filter(part -> part.getName().equals(filename) && part.getSize() > 0)
                    .forEach(part -> {
                        try {
                            contentType.set(part.getContentType());
                            part.getInputStream().transferTo(buffer);
                        } catch (IOException e) {
                            logger.error("Error while transfer file", e);
                        }
                    });
        } catch (ServletException | IOException e) {
            logger.error(e);
            buffer.reset();
        }
        if (buffer.size() != 0) {
            byte[] encodeImg = Base64.encodeBase64(buffer.toByteArray(), false);
            String imgStr = StringUtils.newStringUtf8(encodeImg);
            encodeFile = Optional.of(BASE_64_FILE.formatted(contentType.get(), imgStr));
        }
        return encodeFile;
    }

    private FileEncoder() {
    }
}
