package araliya.pointOfSales.service;

import araliya.pointOfSales.dtos.TransactionDto;

public interface TransactionService {
    String saveTransaction(TransactionDto transactionDto) throws Exception;
    
}
