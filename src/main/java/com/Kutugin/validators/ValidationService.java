package com.Kutugin.validators;

import com.Kutugin.exceptions.BusinessException;

/**
 * Created by dp-ptcstd-49 on 15.02.2019.
 */
public interface ValidationService {

    void validateAge(int age) throws BusinessException;
    void validatePhoneNumber(String phoneNumber) throws BusinessException;
}
