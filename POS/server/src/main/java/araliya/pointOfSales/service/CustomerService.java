package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Customer;

@Service
public interface  CustomerService {
    List<Customer> loadCustomers()throws Exception;
}
