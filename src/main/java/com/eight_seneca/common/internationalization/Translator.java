package com.eight_seneca.common.internationalization;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Translator {

    public static String getMessage(final String code, Object... args) {
        String message;
        try {
            message = ResourceBundle.getBundle("messages", Locale.getDefault()).getString(code);
            if (StringUtils.isBlank(message)) {
                return code;
            }
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            message = new String(bytes, StandardCharsets.UTF_8);
            if (args.length > 0) {
                message = MessageFormat.format(message, args);
            }
        } catch (Exception e) {
            log.error("Get message fail: ", e);
            return code;
        }
        return message;
    }
}
