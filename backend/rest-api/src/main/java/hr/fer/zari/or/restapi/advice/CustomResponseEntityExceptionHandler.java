package hr.fer.zari.or.restapi.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hr.fer.zari.or.restapi.exception.BreedNotFoundException;
import hr.fer.zari.or.restapi.exception.ExceptionResponse;
import hr.fer.zari.or.restapi.exception.InvalidInputException;
import hr.fer.zari.or.restapi.exception.NoDescendantFoundException;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<Object> handleInvalidInputExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDescendantFoundException.class)
	public final ResponseEntity<Object> handleNoDescendantFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(BreedNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(BreedNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}
