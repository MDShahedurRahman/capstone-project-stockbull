package com.mdrahman.stockbull.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Meta-annotation for creating a list of FieldMatch annotations.
 * It allows multiple FieldMatch annotations to be used at the same time.
 */

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch
{
    String message() default "{constraints.field-match}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //Specifies the name of the first field to be compared.
    String first();

    //Specifies the name of the second field to be compared.
    String second();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }
}


