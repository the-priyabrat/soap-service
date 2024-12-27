package com.interland.ipsh.soaps.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class RecordNotExistException extends Exception {

	private static final long serialVersionUID = -5276482991920691683L;

	public RecordNotExistException() {
		super();
	}

	public RecordNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecordNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotExistException(String message) {
		super(message);
	}

	public RecordNotExistException(Throwable cause) {
		super(cause);
	}
	
	

}
