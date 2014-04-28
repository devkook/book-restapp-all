package devfun.bookstore.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import devfun.bookstore.rest.domain.RestError;
import devfun.bookstore.rest.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ResourceNotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public RestError handleResourceNotFound(ResourceNotFoundException ex) {
		RestError error = new RestError(404, "해당 자원을 찾을 수 없습니다.");
		return error;
	}

}
