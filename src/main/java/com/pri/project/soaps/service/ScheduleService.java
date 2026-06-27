package com.pri.project.soaps.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pri.project.soaps.entity.TransactionTable;
import com.pri.project.soaps.repository.TransactionRepository;
import com.pri.project.soaps.utils.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ScheduleService {

	private final TransactionService service;
	private final TransactionRepository repository;

	@Scheduled(fixedDelay = 3000)
	public void scheduleTask() {
		List<TransactionTable> transactionList = repository.findAll();
		transactionList.forEach(transaction -> {
			if (transaction.getTransactionStatus().contentEquals(Constants.PROCESSD)) {
				service.verifyTransaction(transaction);
			}
		});
	}
}