package com.bank.customermanagement.customer_management_service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bank.customermanagement.customer_management_service.dto.ErrorResponseDto;

@RestControllerAdvice // @Controlleradvice + @ResponseBody
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {

		logger.warn("Business validation failed: {}", ex.getMessage());

		ErrorResponseDto errorResponse = new ErrorResponseDto("VALIDATION_ERROR", ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler({  AccessDeniedException.class })
	public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(Exception ex) {

		logger.warn("Access denied: {}", ex.getMessage());

		ErrorResponseDto errorResponse = new ErrorResponseDto("ACCESS_DENIED",
				"You are not authorized to perform this action.");

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {

		logger.error("Unhandled exception occurred", ex);

		ErrorResponseDto errorResponse = new ErrorResponseDto("INTERNAL_SERVER_ERROR",
				"Unexpected error occurred. Please contact support.");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	@ExceptionHandler(BranchDeletionNotAllowedException.class)
	public ResponseEntity<String> handleBranchDeletionException(BranchDeletionNotAllowedException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
