package com.trading.configuration;

import com.trading.Converter.LocalDateTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcContext extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateTimeConverter("dd.MM.yy"));
        //registry.addConverter(new LocalDateTimeConverter("dd.MM.yy HH:mm"));
    }
}
