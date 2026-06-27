package com.pri.project.soaps.service;

import org.pri.project.designationlist.TransactionResponse;
import org.pri.project.designationlist.TransactionsRequest;

import com.pri.project.soaps.entity.TransactionTable;

public interface TransactionService {
	public TransactionResponse makeTransaction(TransactionsRequest request);

	public TransactionResponse verifyTransaction(TransactionTable request);
}
