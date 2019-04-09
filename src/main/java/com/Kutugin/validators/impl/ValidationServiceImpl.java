package com.Kutugin.validators.impl;

import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.validators.ValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
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
        Pattern p = Pattern.compile("(063|095|067|098|050|073)\\d{7}");
        Matcher m = p.matcher(phoneNumber);
        if (!m.matches()) throw new BusinessException("Wrong phone number format!");
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        Pattern p = Pattern.compile("\\w+\\@\\w+\\.(com|ru|ua|net|edu)");
        Matcher m = p.matcher(email);
        if (!m.matches()) throw new BusinessException("Wrong email format!");
    }

    @Override
    public void validateName(String name) throws BusinessException {
        Pattern p = Pattern.compile("\\D{1,20}");
        Matcher m = p.matcher(name);
        if (!m.matches()) throw new BusinessException("Wrong name format!");
    }

    @Override
    public void validateInteger(String input) throws BusinessException {
        Pattern p = Pattern.compile("\\d{1,2}");
        Matcher m = p.matcher(input);
        if (!m.matches()) throw new BusinessException("Wrong integer digit format!");
    }

    @Override
    public void validateDouble(String arg) throws BusinessException {
        Pattern p = Pattern.compile("\\d{1,20}\\.+\\d{1,20}");
        Matcher m = p.matcher(arg);
        if (!m.matches()) throw new BusinessException("Wrong double digit format!");
    }
}