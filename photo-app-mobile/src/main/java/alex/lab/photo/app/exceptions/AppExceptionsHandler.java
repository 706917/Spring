package alex.lab.photo.app.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import alex.lab.photo.app.ui.model.responce.ErrorMessageModel;

@ControllerAdvice
public class AppExceptionsHandler {
	
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handlerUserServiceException(UserServiceException ex, WebRequest request){
		
		ErrorMessageModel errorMessage = new ErrorMessageModel(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
