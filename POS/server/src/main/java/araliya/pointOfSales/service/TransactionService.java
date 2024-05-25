package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.dtos.TransactionDto;
import araliya.pointOfSales.entity.Transaction;


@Service
public interface TransactionService {
    String saveTransaction(TransactionDto transactionDto) throws Exception;
    List<TransactionDto> loadTransactions() throws Exception;
    
}
