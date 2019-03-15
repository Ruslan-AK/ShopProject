package com.Kutugin.validators.impl;

import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.validators.ValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceImplTest {

    private ValidationService validationService = new ValidationServiceImpl();

    @Test
    public void validateAge() throws BusinessException {
        String age = "50";
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateWrongAge() throws BusinessException {
        String age = "150";
        validationService.validateAge(age);
    }

    @Test
    public void validatePhoneNumberSuccess() throws BusinessException {
        String phone = "0677777777";
        validationService.validatePhoneNumber(phone);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneNumberFail() throws BusinessException {
        String phone = "06777777779";
        validationService.validatePhoneNumber(phone);
    }

    @Test
    public void validateEmailSucess() throws BusinessException {
        String email = "test@success.com";
        validationService.validateEmail(email);
    }

    @Test(expected = BusinessException.class)
    public void validateEmailFail() throws BusinessException {
        String email = "testFail@.com";
        validationService.validateEmail(email);
    }

    @Test(expected = BusinessException.class)
    public void validateNameFail() throws BusinessException {
        String name = "Dama95";
        validationService.validateName(name);
    }

    @Test
    public void validateNameSuccess() throws BusinessException {
        String name = "Dama";
        validationService.validateName(name);
    }

    @Test(expected = BusinessException.class)
    public void validateIntegerFail() throws BusinessException {
        String d = "3.6";
        validationService.validateInteger(d);
    }

    @Test
    public void validateIntegerSuccess() throws BusinessException {
        String d = "3";
        validationService.validateInteger(d);
    }

    @Test(expected = BusinessException.class)
    public void validateDoubleFail() throws BusinessException {
        String d = "3";
        validationService.validateDouble(d);
    }

    @Test
    public void validateDoubleSuccess() throws BusinessException {
        String d = "3.7";
        validationService.validateDouble(d);
    }
}