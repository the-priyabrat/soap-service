package com.interland.ipsh.soaps.service;

import org.interland.ipsh.designationlist.TransactionResponse;
import org.interland.ipsh.designationlist.TransactionsRequest;

import com.interland.ipsh.soaps.entity.TransactionTable;

public interface TransactionService {
	public TransactionResponse makeTransaction(TransactionsRequest request);

	public TransactionResponse verifyTransaction(TransactionTable request);
}
