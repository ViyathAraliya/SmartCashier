package araliya.pointOfSales.service;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.dtos.TransactionDto;


@Service
public interface TransactionService {
    String saveTransaction(TransactionDto transactionDto) throws Exception;
    
}
