package com.octopus.service.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * <h1>Message By Locale Utility</h1>
 * Get response messages for all supported languages based on
 * the locale information in each request.
 */
@Service
public class MessageByLocale {

    private MessageSource messageSource;

    @Autowired
    public MessageByLocale(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(id, null, id, locale);
    }

    public String getMessage(String id, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(id, args, id, locale);
    }

}
