package araliya.pointOfSales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import araliya.pointOfSales.dtos.TransactionDto;
import araliya.pointOfSales.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired

    @Override
    public String saveTransaction(TransactionDto transactionDto) throws Exception {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            

            transactionManager.commit(status);
            return "transaction saved succesfully";
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
            
        }
    }
    
}
