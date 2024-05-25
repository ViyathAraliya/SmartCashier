package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Customer;
import araliya.pointOfSales.repository.CustomerRepository;
import araliya.pointOfSales.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> loadCustomers() throws Exception {
       return customerRepository.findAll();
    }
    
}
