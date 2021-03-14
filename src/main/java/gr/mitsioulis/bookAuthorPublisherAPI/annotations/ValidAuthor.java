package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validate that an author with the annotated object's id exists in database
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAuthorValidator.class)
public @interface ValidAuthor {

	String message() default "No author with such id";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
