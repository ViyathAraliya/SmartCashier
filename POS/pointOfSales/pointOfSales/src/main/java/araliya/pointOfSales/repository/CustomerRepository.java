package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    
}
