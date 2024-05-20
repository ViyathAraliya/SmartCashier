package araliya.pointOfSales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Supplier;
import araliya.pointOfSales.entity.Supplier_Item;
import araliya.pointOfSales.repository.SupplieRepository;
import araliya.pointOfSales.service.SupplierService;
import araliya.pointOfSales.service.Supplier_Item_Service;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private Supplier_Item_Service supplier_Item_Service;

    @Autowired
    private SupplieRepository supplieRepository;
   public List<Supplier> getSuppliersByItemID(Long itemID)  throws Exception
   
    {
        List<Supplier_Item> supplier_Items=supplier_Item_Service.loadSupplier_Items(itemID);
        List<Supplier> suppliers=new ArrayList<>();
        for(int i=0;i<supplier_Items.size();i++){
            Supplier supplier=supplieRepository.findById(supplier_Items.get(i).getSupplier_Item_ID().getSupplierID()).orElse(null);
           /*  if(supplier!=null){
                throw new Exception("failed to retrieve a certain supplier");
            }*/
            suppliers.add(supplier);
        }

            return suppliers;
        }

    public Supplier findByName(String name) throws Exception{
        return supplieRepository.findByName(name);
    }

    public Supplier savSupplier(Supplier supplier) throws Exception{
        return supplieRepository.save(supplier);
    }

    }
    

