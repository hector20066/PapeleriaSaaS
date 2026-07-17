package com.hector.papeleriasaas.utils;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ParserUtils {

    public static BigDecimal parseBigDecimal(String value, String fieldName) throws InvalidInputException {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException(fieldName, e);
        }
    }

    public static int parseInteger(String value, String fieldName) throws InvalidInputException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException(fieldName, e);
        }
    }

}
