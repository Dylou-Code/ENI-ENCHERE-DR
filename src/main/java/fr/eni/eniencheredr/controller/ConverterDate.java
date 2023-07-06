package fr.eni.eniencheredr.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ConverterDate implements Converter<String, java.util.Date> {

    @Override
    public java.util.Date convert(String value) {
        try {
            return value == null || value.isBlank() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
