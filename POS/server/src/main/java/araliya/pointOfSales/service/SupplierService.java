package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Supplier;

@Service
public interface SupplierService {
    List<Supplier> getSuppliersByItemID(Long itemID)  throws Exception;
    Supplier findByName(String name) throws Exception;
    Supplier saveSupplier(Supplier supplier) throws Exception;
}
