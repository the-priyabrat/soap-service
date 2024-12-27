package com.interland.ipsh.soaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interland.ipsh.soaps.entity.TransactionTable;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionTable, Long> {

}
