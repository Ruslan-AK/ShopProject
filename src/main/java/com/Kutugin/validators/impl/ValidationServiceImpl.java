package com.Kutugin.validators.impl;

import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.validators.ValidationService;

/**
 * Created by dp-ptcstd-49 on 15.02.2019.
 */
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateAge(int age) throws BusinessException {
        if(age<0||age>200){
            throw new BusinessException("Incorrect input!");
        }
    }
}
