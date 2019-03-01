package com.Kutugin.validators;

import com.Kutugin.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(String age) throws BusinessException;

    void validatePhoneNumber(String phoneNumber) throws BusinessException;

    void validateEmail(String email) throws BusinessException;

    void validateName(String email) throws BusinessException;

    void validateSurname(String email) throws BusinessException;
}
