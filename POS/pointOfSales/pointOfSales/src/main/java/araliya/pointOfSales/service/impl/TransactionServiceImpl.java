package araliya.pointOfSales.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import araliya.pointOfSales.dtos.TransactionDto;
import araliya.pointOfSales.dtos.Transaction_Item_dto;
import araliya.pointOfSales.entity.Customer;
import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Transaction;
import araliya.pointOfSales.entity.Transaction_Item;
import araliya.pointOfSales.repository.ItemRepository;
import araliya.pointOfSales.repository.TransactionRepository;
import araliya.pointOfSales.repository.Transaction_Item_Repository;
import araliya.pointOfSales.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Transaction_Item_Repository transaction_Item_Repository;

    @Autowired
    private ItemRepository itemRepository;



    @Override
    public String saveTransaction(TransactionDto transactionDto) throws Exception {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            Customer customer=transactionDto.getCustomer();
            Date dateTime=transactionDto.getDateTime();

            List<Transaction_Item_dto> transaction_item_dtos=transactionDto.getTransaction_Item_dtos();//contains fields: itemID, qty and amount
            
            Integer int_to=0;
            Long totalAmount = int_to.longValue();

            for(int i=0; i<transaction_item_dtos.size();i++){
                totalAmount=totalAmount+transaction_item_dtos.get(i).getAmount();
            }
            Transaction transaction=new Transaction();
            transaction.setCustomer(customer);
            transaction.setDateTime(dateTime);
            transaction.setTotalAmount(totalAmount);
            
            transaction=transactionRepository.save(transaction);
            
            for(int i=0;i<transaction_item_dtos.size();i++){
                Transaction_Item_dto transaction_Item_dto=transaction_item_dtos.get(i);
                Item item=itemRepository.findById(transaction_Item_dto.getItemID()).orElse(null);
               
                Transaction_Item transaction_Item=new Transaction_Item();
                transaction_Item.setItem(item);
                transaction_Item.setTransaction(transaction);
                transaction_Item.setQty(transaction_Item_dto.getQty());
                transaction_Item.setAmount(transaction_Item.getAmount());
                transaction_Item_Repository.save(transaction_Item);
                

                
            }
            
            transactionManager.commit(status);
            return "transaction saved succesfully";
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
            
        }
    }
    
}
