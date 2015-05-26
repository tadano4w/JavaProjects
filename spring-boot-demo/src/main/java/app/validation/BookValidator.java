package app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {

	public boolean supports(Class<?> candidate) {
		// Book用Validator
		return Book.class.isAssignableFrom(candidate);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "validation.error.notNull");
	}
}