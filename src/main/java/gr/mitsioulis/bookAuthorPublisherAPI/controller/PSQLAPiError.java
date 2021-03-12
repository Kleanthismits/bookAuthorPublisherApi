package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kleanthis mitsioulis
 *
 */
@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PSQLAPiError {

	private long   timestamp = new Date().getTime();
	private int    status;
	private String message;
	private String url;
	private String errorMessage;

	public PSQLAPiError(int status, String message, String url) {

		super();
		this.status = status;
		this.message = message;
		this.url = url;
	}
}
