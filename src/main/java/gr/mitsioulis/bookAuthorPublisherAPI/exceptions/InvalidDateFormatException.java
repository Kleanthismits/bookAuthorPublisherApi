package gr.mitsioulis.bookAuthorPublisherAPI.exceptions;

import lombok.Getter;

public class InvalidDateFormatException extends RuntimeException {

	private static final long serialVersionUID = 4565748277581764045L;
	@Getter
	private String            validFormat;

	public InvalidDateFormatException(String validFormat) {
		this.validFormat = validFormat;
	}

}
