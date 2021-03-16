package gr.mitsioulis.bookAuthorPublisherAPI.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gr.mitsioulis.bookAuthorPublisherAPI.common.errors.ApiError;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(400, "Validation error", request.getContextPath());
		BindingResult result = ex.getBindingResult();
		Map<String, String> validationErrors = result.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (field1, field2) -> field2));
		apiError.setValidationErrors(validationErrors);
		return ResponseEntity.badRequest().body(apiError);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ApiError> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
		ApiError apiError = new ApiError(400, "Constraint violation error: " + ex.getMessage(), request.getContextPath());
		return ResponseEntity.badRequest().body(apiError);
	}

	@ExceptionHandler({ DuplicateISBNException.class })
	public ResponseEntity<ApiError> handleDuplicateISBNException(DuplicateISBNException exception,
			HttpServletRequest request) {

		ApiError apiError = new ApiError(400, exception.getMessage(), request.getServletPath());
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put("isbn", "A book with the same ISBN number already exists");
		apiError.setValidationErrors(validationErrors);
		return ResponseEntity.badRequest().body(apiError);
	}

	@ExceptionHandler({ NoSuchElementException.class, EntityNotFoundException.class })
	public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException exception,
			HttpServletRequest request) {

		ApiError apiError = new ApiError(404, exception.getMessage(), request.getServletPath());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@ExceptionHandler({ PSQLException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handlePSQLException(PSQLException exception, HttpServletRequest request) {

		ApiError apiError = new ApiError(400, "Error while writing to database: " + exception.getMessage(),
				request.getServletPath());
		return ResponseEntity.badRequest().body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(400, ex.getMessage(), request.getContextPath());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
	}

}
