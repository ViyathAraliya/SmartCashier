package araliya.pointOfSales.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemDto {

    private Long itemID;
    private String name;
    private String unit;
    private Long unitPrice;
      private Long categoryID;   
    
}
