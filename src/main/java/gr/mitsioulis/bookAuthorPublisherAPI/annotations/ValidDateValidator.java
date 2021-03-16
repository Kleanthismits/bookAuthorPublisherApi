package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

	private final static String VALIDATED_DATE_FORMAT = "dd/MM/yyyy";
	private Boolean             isOptional;

	@Override
	public void initialize(ValidDate validDate) {
		this.isOptional = validDate.optional();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean validDate = isValidFormat(value);

		return isOptional ? (validDate || (value == null || value.isEmpty())) : validDate;
	}

	private static boolean isValidFormat(String value) {
		LocalDate date = null;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATED_DATE_FORMAT);
			if (value != null) {
				date = LocalDate.parse(value, formatter);
				if (!value.equals(date.format(formatter))) {
					date = null;
				}
			}
		} catch (DateTimeParseException e) {
		}

		return date != null;
	}

}
