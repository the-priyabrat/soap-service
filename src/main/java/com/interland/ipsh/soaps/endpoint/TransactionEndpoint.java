package com.interland.ipsh.soaps.endpoint;

import org.interland.ipsh.designationlist.TransactionResponse;
import org.interland.ipsh.designationlist.TransactionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.interland.ipsh.soaps.service.ScheduleService;
import com.interland.ipsh.soaps.service.TransactionService;

@Endpoint
public class TransactionEndpoint {

	private final String NAMESPACE = "http://www.example.org/designationList";

	@Autowired
	private TransactionService service;
	@Autowired
	private ScheduleService scheduleService;

	@PayloadRoot(namespace = NAMESPACE, localPart = "transactionsRequest")
	@ResponsePayload
	public TransactionResponse saveTransaction(@RequestPayload TransactionsRequest request) {
		scheduleService.scheduleTask();
		return service.makeTransaction(request);
	}
}
