package com.nish.store.customValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidation implements ConstraintValidator<ImageValidation, String> {
    private Logger logger = LoggerFactory.getLogger(ImageNameValidation.class);
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("Message from isValid : {} ", value);
        if(value.isBlank())
            return false;
        else
            return true;
//        return false;
    }
}
