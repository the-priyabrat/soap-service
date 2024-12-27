package com.interland.ipsh.soaps.service;

import java.time.LocalDateTime;

import org.interland.ipsh.designationlist.TransactionResponse;
import org.interland.ipsh.designationlist.TransactionsRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.interland.ipsh.soaps.entity.TransactionTable;
import com.interland.ipsh.soaps.repository.TransactionRepository;
import com.interland.ipsh.soaps.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransactionServiceImplementation implements TransactionService {
	private final TransactionRepository repository;

	@Override
	public TransactionResponse makeTransaction(TransactionsRequest request) {
		try {
			TransactionTable transaction = new TransactionTable();
			BeanUtils.copyProperties(request.getTransaction(), transaction);
			if (transaction.getTransactionAmount() == null || transaction.getTransactionAmount() == 0) {
				transaction = transaction.toBuilder().transactionStatus(Constants.FAILED)
						.transactionTime(LocalDateTime.now()).build();
			} else {
				transaction = transaction.toBuilder().transactionStatus(Constants.PROCESSD)
						.transactionTime(LocalDateTime.now()).build();
			}
			repository.save(transaction);
			TransactionResponse response = new TransactionResponse();
			response.setAmount(request.getTransaction().getTransactionAmount());
			response.setStatus(request.getTransaction().getTransactionStatus());
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new TransactionResponse();
	}

	@Override
	public TransactionResponse verifyTransaction(TransactionTable request) {
		try {
			TransactionTable transaction = new TransactionTable();
			BeanUtils.copyProperties(request, transaction);
			transaction = transaction.toBuilder().transactionStatus(Constants.SUCCESS).build();
			if (transaction.getTransactionAmount() != 0) {
				repository.save(transaction);
			}
			TransactionResponse response = new TransactionResponse();
			response.setAmount(request.getTransactionAmount());
			response.setStatus(request.getTransactionStatus());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new TransactionResponse();
	}

}
