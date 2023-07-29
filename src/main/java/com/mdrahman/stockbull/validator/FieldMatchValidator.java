package com.mdrahman.stockbull.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    // Initialize method

    /**
     * Initializes the validator with the attributes from the FieldMatch annotation.
     * It is called once when the validator is created.
     *
     * @param constraintAnnotation The FieldMatch annotation instance.
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

// isValid method

    /**
     * Validates the two fields to ensure their values match.
     * It is called by the validator framework to perform the validation.
     *
     * @param value   The object being validated.
     * @param context The validation context.
     * @return true if the fields are valid (i.e., their values match), false otherwise.
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            // Get the values of the two fields using reflection
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            // Check if both fields are null or their values are equal
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            // Ignore any exceptions that may occur during validation
        }
        return true; // Return true if an exception occurs (fail-safe behavior)
    }
}
