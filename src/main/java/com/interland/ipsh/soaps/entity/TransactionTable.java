package com.interland.ipsh.soaps.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name = "TRANSACTION_TABLE")
public class TransactionTable {

	@Id
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;
	@Column(name = "TRANSACTION_STATUS")
	private String transactionStatus;
	@Column(name = "TRANSACTION_AMOUNT")
	private Long transactionAmount;
	@Column(name = "TRANSACTION_TIME")
	private LocalDateTime transactionTime;
}
