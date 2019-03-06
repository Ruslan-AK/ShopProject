package com.Kutugin.validators.impl;

import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.validators.ValidationService;
import org.junit.Test;

public class ValidationServiceImplTest {

    private ValidationService validationService = new ValidationServiceImpl();

    @Test
    public void validateAge() throws BusinessException {
       //when
        String age = "50";
        validationService.validateAge(age);

    }

    @Test(expected = BusinessException.class)
    public void validateWrongAge() throws BusinessException {
        //when
        String age = "150";
        validationService.validateAge(age);

    }
}