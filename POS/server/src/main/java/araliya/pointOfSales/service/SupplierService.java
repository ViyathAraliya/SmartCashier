package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Supplier;

@Service
public interface SupplierService {
    List<Supplier> getSuppliersByItemID(Long id)  throws Exception;
}
