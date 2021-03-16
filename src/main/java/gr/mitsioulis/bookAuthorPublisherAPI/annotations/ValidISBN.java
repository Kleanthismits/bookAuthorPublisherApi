package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validate that the annotated string isbn is not already existing in database
 */

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidIsbnValidator.class)
public @interface ValidISBN {

	String message() default "A book with the same ISBN number already exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean optional() default false;

	boolean checkDuplicate() default true;

}
