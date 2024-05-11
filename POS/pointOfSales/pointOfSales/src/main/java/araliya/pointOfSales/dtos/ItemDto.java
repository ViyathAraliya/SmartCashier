package araliya.pointOfSales.dtos;

import java.util.List;

import araliya.pointOfSales.entity.ItemCategory;
import araliya.pointOfSales.entity.Stock;
import araliya.pointOfSales.entity.Supplier;
import lombok.Data;

@Data
public class ItemDto {
  
    private Long itemID;

    private TestDtos tesaDto;
    

    private String name;

   
    private String unit;

    
    private ItemCategory category;

    private Stock stock;

    private Supplier supplier;

    private Long qty;

 

}
