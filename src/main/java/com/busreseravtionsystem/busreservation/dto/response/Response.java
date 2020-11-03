/**
 * 
 */
package com.busreseravtionsystem.busreservation.dto.response;

import com.busreseravtionsystem.busreservation.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author a.guindo
 *
 */
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
	public Status status;

	public T payload;

	public Object errors;

	public Object metadata;

	public static <T> Response<T> badRequest() {

		Response<T> response = new Response<>();
		response.setStatus(Status.BAD_REQUEST);

		return response;

	}

	public static <T> Response<T> ok() {

		Response<T> response = new Response<T>();
		response.setStatus(Status.OK);
		return response;
	}

	public static <T> Response<T> unauthorized() {

		Response<T> response = new Response<T>();
		response.setStatus(Status.UNAUTHORIZED);
		return response;

	}

	public static <T> Response<T> validateException() {
		Response<T> response = new Response<>();

		response.setStatus(Status.VALIDATION_EXCEPTION);
		return response;

	}

	public static <T> Response<T> exception() {

		Response<T> response = new Response<>();
		response.setStatus(Status.EXCEPTION);
		return response;

	}

	public static <T> Response<T> wrongCredentials() {

		Response<T> response = new Response<>();
		response.setStatus(Status.WRONG_CREDENTIALS);
		return response;
	}

	public static <T> Response<T> accessDenied() {

		Response<T> response = new Response<>();

		response.setStatus(Status.ACCESS_DENIED);
		return response;
	}

	public static <T> Response<T> notFound() {

		Response<T> response = new Response<>();
		response.setStatus(Status.NOT_FOUND);
		return response;
	}

	public static <T> Response<T> duplicateEntity() {

		Response<T> response = new Response<>();
		response.setStatus(Status.DUPLICATE_ENTITY);

		return response;
	}

	public enum Status {

		OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND,
		DUPLICATE_ENTITY
	}

	public void addErrorMsgToResponse(String msgError, Exception e) {

		ResponseError responseError = new ResponseError();
		responseError.setTimeStamp(DateUtils.today());
		responseError.setDetails(msgError);
		responseError.setMessage(e.getMessage());
		setErrors(responseError);
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(content = Include.NON_NULL)
	public static class PageMetada {

		public final int size;

		public final long totalElements;
		public final int totalPages;

		public final int number;

		public PageMetada(int size, long totalElements, int totalPages, int number) {
			super();
			this.size = size;
			this.totalElements = totalElements;
			this.totalPages = totalPages;
			this.number = number;
		}

	}
}
