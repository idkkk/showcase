package org.rubik.order.web.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<?> handleAnyException(Exception e) {
		return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ================================================== private method
	private ResponseEntity<?> errorResponse(Throwable throwable, HttpStatus status) {
		LOGGER.error("Exception Info: {}", Throwables.getStackTraceAsString(throwable));
		ModelMap result = new ModelMap();
		if (null != throwable) {
			result.addAttribute("errcode", status.value());
			result.addAttribute("errmsg", Throwables.getStackTraceAsString(throwable));
			return response(result, status);
		} else {
			result.addAttribute("errcode", status.value());
			result.addAttribute("errmsg", status.getReasonPhrase());
			return response(result, status);
		}
	}

	private <T> ResponseEntity<T> response(T body, HttpStatus status) {
		return new ResponseEntity<T>(body, new HttpHeaders(), status);
	}
}