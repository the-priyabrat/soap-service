package com.pri.project.soaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pri.project.soaps.entity.TransactionTable;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionTable, Long> {

}
