package com.Kutugin.validators.impl;

import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.validators.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateAge(String age) throws BusinessException {
        int ageInt;
        try {
            ageInt = Integer.valueOf(age);
        } catch (NumberFormatException ex) {
            throw new BusinessException("Only digits!");
        }
        if (ageInt < 10 || ageInt > 100) {
            throw new BusinessException("Incorrect age!");
        }
    }

    @Override
    public void validatePhoneNumber(String phoneNumber) throws BusinessException {
        Pattern p = Pattern.compile("(063|095|067)\\d{7}");
        Matcher m = p.matcher(phoneNumber);
        if (!m.matches()) throw new BusinessException("Wrong phone number format!");
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        Pattern p = Pattern.compile(".+\\@.+\\.(com|ru|ua|net)");
        Matcher m = p.matcher(email);
        if (!m.matches()) throw new BusinessException("Wrong email format!");
    }
}