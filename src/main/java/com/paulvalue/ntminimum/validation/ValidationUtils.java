package com.paulvalue.ntminimum.validation;

import com.paulvalue.ntminimum.exception.InvalidInputException;
import org.springframework.web.multipart.MultipartFile;

public final class ValidationUtils {

    private ValidationUtils() {}

    public static void validateInput(MultipartFile file, int n) {
        if (file == null || file.isEmpty()) {
            throw new InvalidInputException("File is empty");
        }
        if (n <= 0) {
            throw new InvalidInputException("N must be positive");
        }
    }

    public static void validateNValue(int n, int numbersCount) {
        if (n > numbersCount) {
            throw new InvalidInputException(
                    "N exceeds number of elements in file");
        }
    }
}
